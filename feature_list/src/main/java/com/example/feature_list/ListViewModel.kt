package com.example.feature_list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.navigation.Navigation
import com.example.core_domain.usecase.settings.ObserveSearchItemUseCase
import com.example.core_domain.usecase.settings.SaveSearchItemUseCase
import com.example.core_domain.usecase.tasks.*
import com.example.core_domain.usecase.users.GetCurrentUserUseCase
import com.example.core_domain.usecase.users.SignOutUseCase
import com.example.feature_list.mappers.SearchItemDomainToUiMapper
import com.example.feature_list.mappers.SearchItemUiToDomainMapper
import com.example.feature_list.mappers.TaskDomainToUIMapper
import com.example.feature_list.mappers.TaskUIToDomainParamsMapper
import com.example.feature_list.models.StateTasksUI
import com.example.feature_list.rvlist.TaskUI
import com.example.feature_list.rvsearchsuggestions.SuggestionsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ListViewModel(
    private val userId: String,
    private val communication: CommunicationList,
    private val navigation: Navigation,
    private val taskDomainToUIMapper: TaskDomainToUIMapper,
    private val taskUiToDomainParamsMapper: TaskUIToDomainParamsMapper,
    private val searchItemDomainToUiMapper: SearchItemDomainToUiMapper,
    private val searchItemUiToDomainMapper: SearchItemUiToDomainMapper,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val markForDeletionTaskUseCase: MarkForDeletionTaskUseCase,
    private val uncheckToDeleteTaskUseCase: UncheckToDeleteTaskUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val observeSearchItemUseCase: ObserveSearchItemUseCase,
    private val saveSearchItemUseCase: SaveSearchItemUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch(Dispatchers.IO) {
            communication.mapTasks(StateTasksUI.Loading)
            val searchText = communication.observeSearchText()
            observeTasksUseCase(userId).combine(searchText) { tasksDomain, text ->
                text?.let {
                    tasksDomain.filter { task -> task.text.startsWith(prefix = text, ignoreCase = true) }
                } ?: tasksDomain
            }.collect { tasksDomain ->
                val tasksUI = tasksDomain.map { taskDomain -> taskDomainToUIMapper.transform(taskDomain)}
                communication.mapTasks(ChooseStateTaskUI(tasksUI).map())
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            val currentUser = getCurrentUserUseCase()
            currentUser?.let { userDomain -> communication.mapUserEmail(userDomain.email) }
        }
        viewModelScope.launch(Dispatchers.IO) {
            observeSearchItemUseCase(SEARCH_SECTION).map { listDomain ->
                listDomain.map { item -> searchItemDomainToUiMapper.transform(item) }
            }.collect { listUI -> communication.mapListSuggestions(listUI) }
        }
    }

    fun setIsDoneTask(taskUI: TaskUI, value: Boolean) {
        if (taskUI.isDone == value) return
        scope.launch {
            val taskDomainParams = taskUiToDomainParamsMapper.transform(taskUI.copy(isDone = value))
            editTaskUseCase(taskDomainParams)
        }
    }

    fun deleteTask(taskUI: TaskUI) {
        scope.launch {
            val taskDomainParams = taskUiToDomainParamsMapper.transform(taskUI)
            deleteTaskUseCase(taskDomainParams)
        }
    }

    fun markForDeletionTask(taskUI: TaskUI) {
        scope.launch {
            val taskDomainParams = taskUiToDomainParamsMapper.transform(taskUI)
            markForDeletionTaskUseCase(taskDomainParams)
        }
    }

    fun uncheckToDeleteTaskUseCase(taskUI: TaskUI) {
        scope.launch {
            val taskDomainParams = taskUiToDomainParamsMapper.transform(taskUI)
            uncheckToDeleteTaskUseCase(taskDomainParams)
        }
    }

    fun signOut() {
        navigation.showUserSelectReplace()
        scope.launch  {
            signOutUseCase()
        }
    }

    fun showDetails(taskId: Long = TaskUI.NEW_TASK_ID) {
        navigation.showTaskDetailsAdd(userId, taskId)
    }

    fun showFilters() {
        navigation.showTasksFiltersModal()
    }

    fun showSorting() {
        navigation.showTasksSortingModal()
    }

    fun searchTaskByText(text: String) {
        communication.mapSearchText(text)
        if (text.isEmpty()) return
        scope.launch  {
            val searchItemUI = communication.getSuggestionsItem(text)
            val searchItemDomain = searchItemUiToDomainMapper.transform(searchItemUI)
            saveSearchItemUseCase(searchItemDomain)
        }
    }

    fun observeTasks(owner: LifecycleOwner, observer: Observer<StateTasksUI>) {
        communication.observeTasks(owner, observer)
    }

    fun observeListSuggestions(owner: LifecycleOwner, observer: Observer<List<SuggestionsItem>>) {
        communication.observeListSuggestions(owner, observer)
    }

    fun observeUserEmail(owner: LifecycleOwner, observer: Observer<String>) {
        communication.observeUserEmail(owner, observer)
    }

    companion object {
        private const val SEARCH_SECTION = "tasks"
    }
}

class ChooseStateTaskUI(private val source: List<TaskUI>) {
    private val empty: StateTasksUI by lazy { StateTasksUI.Empty }
    private val success: StateTasksUI by lazy { StateTasksUI.Success(source) }
    fun map(): StateTasksUI = if (source.isEmpty()) empty else success
}

