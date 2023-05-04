package com.example.feature_tasks_filters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_domain.usecase.settings.FetchFiltersUseCase
import com.example.core_domain.usecase.settings.SaveHideCompletedFiltersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TasksFiltersViewModel(
    private val communicationTasksFilters: CommunicationTasksFilters,
    private val fetchTasksFiltersUseCase: FetchFiltersUseCase,
    private val saveHideCompletedFiltersUseCase: SaveHideCompletedFiltersUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch(Dispatchers.IO) {
            val filters = fetchTasksFiltersUseCase()
            communicationTasksFilters.mapHideCompleted(filters.hideCompleted)
        }
    }

    fun saveFilters() {
        scope.launch {
            val saveParams = communicationTasksFilters.getSaveParams()
            saveHideCompletedFiltersUseCase(saveParams.hideCompleted)
        }
        closeScreen()
    }

    fun setHideCompleted() {
        val hideCompleted = communicationTasksFilters.getHideCompleted()
        communicationTasksFilters.mapHideCompleted(!hideCompleted)
    }

    fun observeHideCompleted(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationTasksFilters.observeHideCompleted(owner, observer)
    }

    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationTasksFilters.observeCloseScreen(owner, observer)
    }

    private fun closeScreen() {
       communicationTasksFilters.mapCloseScreen(true)
    }
}