package com.example.todoapp.presentation.tasks

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.presentation.auth.mappers.UserDomainToUIMapper
import com.example.todoapp.presentation.common.navigation.NavigationGraph
import com.example.todoapp.presentation.tasks.mappers.TaskDomainToUIMapper
import com.example.todoapp.presentation.tasks.mappers.TaskUIToDomainParamsMapper
import com.example.todoapp.presentation.tasks.models.StateSettingHideCompletedUI
import com.example.todoapp.presentation.tasks.models.StateTasksUI
import com.example.todoapp.presentation.tasks.models.StateTitleUI
import com.example.todoapp.presentation.tasks.models.TaskUI
import com.example.todoapp.usecase.auth.GetCurrentUserUseCase
import com.example.todoapp.usecase.auth.SignOutUseCase
import com.example.todoapp.usecase.settings.ObserveSettingHideCompletedUseCase
import com.example.todoapp.usecase.settings.SaveSettingHideCompletedUseCase
import com.example.todoapp.usecase.tasks.EditTaskUseCase
import com.example.todoapp.usecase.tasks.ObserveCompletedTasksUseCase
import com.example.todoapp.usecase.tasks.ObserveTasksUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TasksViewModel(
    private val userId: String,
    private val communication: CommunicationTasks,
    private val taskDomainToUIMapper: TaskDomainToUIMapper,
    private val taskUiToDomainParamsMapper: TaskUIToDomainParamsMapper,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val observeCompletedTasksUseCase: ObserveCompletedTasksUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val observeSettingHideCompletedUseCase: ObserveSettingHideCompletedUseCase,
    private val saveSettingHideCompletedUseCase: SaveSettingHideCompletedUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val userDomainToUIMapper: UserDomainToUIMapper
): ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        viewModelScope.launch((Dispatchers.IO)) {
            communication.mapTasks(StateTasksUI.Loading)
            observeTasksUseCase(userId).collect { tasksDomain ->
                val tasksUI = tasksDomain.map { taskDomain -> taskDomainToUIMapper.transform(taskDomain)}
                communication.mapTasks(ChooseStateTaskUI(tasksUI).map())
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            communication.mapTitle(StateTitleUI.Loading)
            observeCompletedTasksUseCase(userId).collect { completedTasks ->
                communication.mapTitle(ChooseStateTitleUI(completedTasks).map())
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            observeSettingHideCompletedUseCase().collect { isEnabled ->
                communication.mapFilterCompleted(
                    StateSettingHideCompletedUI.Initial(
                        isEnabled, ChooseStateHideCompletedTaskUI(isEnabled).map()
                    )
                )
            }
        }

        viewModelScope.launch((Dispatchers.IO)) {
            val currentUser = getCurrentUserUseCase()
            communication.mapUser(currentUser?.let { userDomainToUIMapper.transform(it) })
        }
    }

    fun setIsDoneTask(taskUI: TaskUI, value: Boolean) {
        if (taskUI.isDone == value) return
        scope.launch {
            val taskDomainParams = taskUiToDomainParamsMapper.transform(taskUI.copy(isDone = value))
            editTaskUseCase(taskDomainParams)
        }
    }

    fun saveSettingHideCompleted(hideCompleted: Boolean) {
        scope.launch  {
            saveSettingHideCompletedUseCase(hideCompleted)
        }
    }

    fun signOut() {
        scope.launch  {
            signOutUseCase()
            communication.mapNavigation(NavigationGraph.tasksToAuth)
        }
    }

    fun observeTasks(owner: LifecycleOwner, observer: Observer<StateTasksUI>) {
        communication.observeTasks(owner, observer)
    }

    fun observeTitle(owner: LifecycleOwner, observer: Observer<StateTitleUI>) {
        communication.observeTitle(owner, observer)
    }

    fun observeFilterCompleted(owner: LifecycleOwner, observer: Observer<StateSettingHideCompletedUI>) {
        communication.observeFilterCompleted(owner, observer)
    }

    fun observeNavigate(owner: LifecycleOwner, observer: Observer<NavigationGraph>) {
        communication.observeNavigation(owner, observer)
    }
}


class ChooseStateHideCompletedTaskUI(private val source: Boolean) {
    private val on: StateSettingHideCompletedUI by lazy { StateSettingHideCompletedUI.On }
    private val off: StateSettingHideCompletedUI by lazy { StateSettingHideCompletedUI.Off }
    fun map(): StateSettingHideCompletedUI = if (source) on else off
}

class ChooseStateTaskUI(private val source: List<TaskUI>) {
    private val empty: StateTasksUI by lazy { StateTasksUI.Empty }
    private val success: StateTasksUI by lazy { StateTasksUI.Success(source) }
    fun map(): StateTasksUI = if (source.isEmpty()) empty else success
}

class ChooseStateTitleUI(private val source: Int) {
    private val empty: StateTitleUI by lazy { StateTitleUI.Empty }
    private val success: StateTitleUI by lazy { StateTitleUI.Success(source) }
    fun map(): StateTitleUI = if (source == 0) empty else success
}
