package com.example.todoapp.presentation.tasks

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.presentation.tasks.mappers.TaskDomainToUIMapper
import com.example.todoapp.presentation.tasks.mappers.TaskUIToDomainParamsMapper
import com.example.todoapp.presentation.tasks.models.StateSettingHideCompletedUI
import com.example.todoapp.presentation.tasks.models.StateTasksUI
import com.example.todoapp.presentation.tasks.models.StateTitleUI
import com.example.todoapp.presentation.tasks.models.TaskUI
import com.example.todoapp.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TasksViewModel(
    private val communication: CommunicationTasks,
    private val domainToUIMapper: TaskDomainToUIMapper,
    private val uiToDomainParamsMapper: TaskUIToDomainParamsMapper,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val observeCompletedTasksUseCase: ObserveCompletedTasksUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val observeSettingHideCompletedUseCase: ObserveSettingHideCompletedUseCase,
    private val saveSettingHideCompletedUseCase: SaveSettingHideCompletedUseCase
): ViewModel() {

    init {
        viewModelScope.launch((Dispatchers.IO)) {
            communication.mapTasks(StateTasksUI.Loading)
            delay(1000)
            observeTasksUseCase().collect { tasksDomain ->
                val tasksUI = tasksDomain.map { taskDomain -> domainToUIMapper.transform(taskDomain)}
                communication.mapTasks(ChooseStateTaskUI(tasksUI).map())
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            communication.mapTitle(StateTitleUI.Loading)
            delay(1000)
            observeCompletedTasksUseCase().collect { completedTasks ->
                communication.mapTitle(ChooseStateTitleUI(completedTasks).map())
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            observeSettingHideCompletedUseCase().collect { isEnabled ->
                communication.mapFilterCompleted(
                    StateSettingHideCompletedUI.Initial(
                        isEnabled, ChooseStateHideCompletedTaskUI().map(isEnabled)
                    )
                )
            }
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

    fun setIsDoneTask(taskUI: TaskUI, value: Boolean) {
        if (taskUI.isDone == value) return
        viewModelScope.launch(Dispatchers.IO) {
            val taskDomainParams = uiToDomainParamsMapper.transform(taskUI.copy(isDone = value))
            editTaskUseCase(taskDomainParams)
        }
    }

    fun saveSettingHideCompleted(hideCompleted: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveSettingHideCompletedUseCase(hideCompleted)
        }
    }
}

class ChooseStateHideCompletedTaskUI {
    private val on: StateSettingHideCompletedUI by lazy { StateSettingHideCompletedUI.On }
    private val off: StateSettingHideCompletedUI by lazy { StateSettingHideCompletedUI.Off }
    fun map(source: Boolean): StateSettingHideCompletedUI = if (source) on else off
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
