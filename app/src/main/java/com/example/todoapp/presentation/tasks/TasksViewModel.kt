package com.example.todoapp.presentation.tasks

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.presentation.auth.mappers.UserDomainToUIMapper
import com.example.todoapp.presentation.auth.models.UserUI
import com.example.todoapp.presentation.common.navigation.newnav.NavigationCommunication
import com.example.todoapp.presentation.common.navigation.newnav.NavigationStrategy
import com.example.todoapp.presentation.common.navigation.newnav.Screen
import com.example.todoapp.presentation.common.navigation.newnav.ScreenModal
import com.example.todoapp.presentation.tasks.mappers.TaskDomainToUIMapper
import com.example.todoapp.presentation.tasks.mappers.TaskUIToDomainParamsMapper
import com.example.todoapp.presentation.tasks.models.StateSettingHideCompletedUI
import com.example.todoapp.presentation.tasks.models.StateTasksUI
import com.example.todoapp.presentation.tasks.models.TaskUI
import com.example.todoapp.usecase.auth.GetCurrentUserUseCase
import com.example.todoapp.usecase.auth.SignOutUseCase
import com.example.todoapp.usecase.settings.ObserveSettingHideCompletedUseCase
import com.example.todoapp.usecase.settings.SaveSettingHideCompletedUseCase
import com.example.todoapp.usecase.tasks.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TasksViewModel(
    private val userId: String,
    private val communication: CommunicationTasks,
    private val navigationCommunication: NavigationCommunication.Base,
    private val taskDomainToUIMapper: TaskDomainToUIMapper,
    private val taskUiToDomainParamsMapper: TaskUIToDomainParamsMapper,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val observeCountCompletedTasksUseCase: ObserveCountCompletedTasksUseCase,
    private val observeCountNotCompletedTasksImportantUseCase: ObserveCountNotCompletedTasksImportantUseCase,
    private val observeCountNotCompletedTasksUseCase: ObserveCountNotCompletedTasksUseCase,
    private val editTaskUseCase: EditTaskUseCase,
//    private val observeSettingHideCompletedUseCase: ObserveSettingHideCompletedUseCase,
//    private val saveSettingHideCompletedUseCase: SaveSettingHideCompletedUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val userDomainToUIMapper: UserDomainToUIMapper
): ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch((Dispatchers.IO)) {
            communication.mapTasks(StateTasksUI.Loading)
            observeTasksUseCase(userId).collect { tasksDomain ->
                val tasksUI = tasksDomain.map { taskDomain -> taskDomainToUIMapper.transform(taskDomain)}
                communication.mapTasks(ChooseStateTaskUI(tasksUI).map())
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            observeCountNotCompletedTasksImportantUseCase(userId).collect { cntTasks ->
                communication.mapCntTasksImportantNotCompleted(cntTasks)
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            observeCountNotCompletedTasksUseCase(userId).collect { cntTasks ->
                communication.mapCntTasksNotCompleted(cntTasks)
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            observeCountCompletedTasksUseCase(userId).collect { cntTasks ->
                communication.mapCntTasksCompleted(cntTasks)
            }
        }

//        viewModelScope.launch(Dispatchers.IO) {
//            observeSettingHideCompletedUseCase().collect { isEnabled ->
//                communication.mapFilterCompleted(
//                    StateSettingHideCompletedUI.Initial(
//                        isEnabled, ChooseStateHideCompletedTaskUI(isEnabled).map()
//                    )
//                )
//            }
//        }

        viewModelScope.launch((Dispatchers.IO)) {
            val currentUser = getCurrentUserUseCase()
            currentUser?.let { userDomain -> userDomainToUIMapper.transform(userDomain) }
                ?.let { userUI -> communication.mapUser(userUI) }
        }
    }


    fun setIsDoneTask(taskUI: TaskUI, value: Boolean) {
        if (taskUI.isDone == value) return
        scope.launch {
            val taskDomainParams = taskUiToDomainParamsMapper.transform(taskUI.copy(isDone = value))
            editTaskUseCase(taskDomainParams)
        }
    }

//    fun saveSettingHideCompleted(hideCompleted: Boolean) {
//        scope.launch  {
//            saveSettingHideCompletedUseCase(hideCompleted)
//        }
//    }

    fun signOut() {
        navigationCommunication.map(NavigationStrategy.Replace(Screen.SelectUser))
        scope.launch  {
            signOutUseCase()
        }
    }

    fun observeUser(owner: LifecycleOwner, observer: Observer<UserUI>) {
        communication.observeUser(owner, observer)
    }

    fun observeTasks(owner: LifecycleOwner, observer: Observer<StateTasksUI>) {
        communication.observeTasks(owner, observer)
    }

    fun observeCntTasksImportantNotCompleted(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observeCntTasksImportantNotCompleted(owner, observer)
    }

    fun observeCntTasksNotCompleted(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observeCntTasksNotCompleted(owner, observer)
    }

    fun observeCntTasksCompleted(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observeCntTasksCompleted(owner, observer)
    }

//    fun observeFilterCompleted(owner: LifecycleOwner, observer: Observer<StateSettingHideCompletedUI>) {
//        communication.observeFilterCompleted(owner, observer)
//    }

    fun showDetails(taskId: Long = TaskUI.NEW_TASK_ID) {
        navigationCommunication.map(NavigationStrategy.Add(Screen.Details(taskId, userId)))
    }

    fun showFilters() {
        navigationCommunication.map(NavigationStrategy.Modal(ScreenModal.Filters))
    }

}

//class ChooseStateHideCompletedTaskUI(private val source: Boolean) {
//    private val on: StateSettingHideCompletedUI by lazy { StateSettingHideCompletedUI.On }
//    private val off: StateSettingHideCompletedUI by lazy { StateSettingHideCompletedUI.Off }
//    fun map(): StateSettingHideCompletedUI = if (source) on else off
//}

class ChooseStateTaskUI(private val source: List<TaskUI>) {
    private val empty: StateTasksUI by lazy { StateTasksUI.Empty }
    private val success: StateTasksUI by lazy { StateTasksUI.Success(source) }
    fun map(): StateTasksUI = if (source.isEmpty()) empty else success
}

