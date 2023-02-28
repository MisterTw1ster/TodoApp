package com.example.todoapp.presentation.tasks

import androidx.lifecycle.*
import com.example.todoapp.presentation.tasks.mappers.TaskDomainToUIMapper
import com.example.todoapp.presentation.tasks.models.StateTasksUI
import com.example.todoapp.presentation.tasks.models.TaskUI
import com.example.todoapp.usecase.ObserveTasksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TasksViewModel(
    private val communication: CommunicationTasks,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val domainToUIMapper: TaskDomainToUIMapper
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
    }

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            communication.mapTasks(StateTasksUI.Loading)
//            delay(1000)
//            observeTasksUseCase().collect { tasksDomain ->
//                communication.mapTasks(ChooseStateTaskUI(tasksDomain).map())
//            }
//        }
//        viewModelScope.launch(Dispatchers.IO) {
//            observeStatusUseCase().collect { e ->
//                when (e) {
//                    "Сервис недоступен" -> communication.mapStatus(StateErrorUI.Show(e))
//                    else -> communication.mapStatus(StateErrorUI.Hide)
//
//                }
//
//            }
//        }
//        viewModelScope.launch(Dispatchers.IO) {
//            observeSettingHideCompletedUseCase().collect { isEnabled ->
//                communication.mapFilterCompleted(
//                    StateSettingHideCompletedUI.Initial(
//                        isEnabled, ChooseStateShowOnlyNotDoneUI().map(isEnabled)
//                    )
//                )
//            }
//        }
//        viewModelScope.launch(Dispatchers.IO) {
//            communication.mapTitle(StateTitleUI.Loading)
//            observeCompletedTasksUseCase().collect { completedTasks ->
//                val size = completedTasks.size
//                communication.mapTitle(ChooseStateTitleUI(size).map())
//            }
//        }
//    }

    fun observeTasks(owner: LifecycleOwner, observer: Observer<StateTasksUI>) {
        communication.observeTasks(owner, observer)
    }

//    fun observeStatus(owner: LifecycleOwner, observer: Observer<StateErrorUI>) {
//        communication.observeStatus(owner, observer)
//    }
//
//    fun observeFilterCompleted(owner: LifecycleOwner, observer: Observer<StateSettingHideCompletedUI>) {
//        communication.observeFilterCompleted(owner, observer)
//    }
//
//    fun observeTitle(owner: LifecycleOwner, observer: Observer<StateTitleUI>) {
//        communication.observeTitle(owner, observer)
//    }

//    fun setIsDoneTask(taskUI: TaskUI, value: Boolean) {
//        viewModelScope.launch(Dispatchers.IO) {
//            if (taskUI.isDone != value) {
//                setIsDoneTaskUseCase(taskUI.id, value)
//            }
//        }
//    }
//
//    fun setIsFavorite(taskUI: TaskUI) {
//        viewModelScope.launch(Dispatchers.IO) {
//            setIsFavoriteTaskUseCase(taskUI.id)
//        }
//    }
//
//    fun saveSettingHideCompleted(hideCompleted: Boolean) {
//        viewModelScope.launch(Dispatchers.IO) {
//            saveSettingHideCompletedUseCase(hideCompleted)
//        }
//    }
}

//class ChooseStateShowOnlyNotDoneUI {
//    private val on: StateSettingHideCompletedUI by lazy { StateSettingHideCompletedUI.On }
//    private val off: StateSettingHideCompletedUI by lazy { StateSettingHideCompletedUI.Off }
//    fun map(source: Boolean): StateSettingHideCompletedUI = if (source) on else off
//}
//
class ChooseStateTaskUI(private val source: List<TaskUI>) {
    private val empty: StateTasksUI by lazy { StateTasksUI.Empty }
    private val success: StateTasksUI by lazy { StateTasksUI.Success(source) }
    fun map(): StateTasksUI = if (source.isEmpty()) empty else success
}
//
//class ChooseStateTitleUI(private val source: Int) {
//    private val empty: StateTitleUI by lazy { StateTitleUI.Empty }
//    private val success: StateTitleUI by lazy { StateTitleUI.Success(source) }
//    fun map(): StateTitleUI = if (source == 0) empty else success
//}
