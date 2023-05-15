package com.example.feature_dialogs.taskFilter.viewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.settings.FetchFiltersUseCase
import com.example.domain.usecase.settings.SaveHideCompletedFiltersUseCase
import com.example.feature_dialogs.taskFilter.view.CommunicationTaskFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TaskFilterViewModel(
    private val communicationTaskFilter: CommunicationTaskFilter,
    private val fetchTasksFiltersUseCase: FetchFiltersUseCase,
    private val saveHideCompletedFiltersUseCase: SaveHideCompletedFiltersUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch(Dispatchers.IO) {
            val filters = fetchTasksFiltersUseCase()
            communicationTaskFilter.mapHideCompleted(filters.hideCompleted)
        }
    }

    fun saveFilters() {
        scope.launch {
            val saveParams = communicationTaskFilter.getSaveParams()
            saveHideCompletedFiltersUseCase(saveParams.hideCompleted)
        }
        closeScreen()
    }

    fun setHideCompleted() {
        val hideCompleted = communicationTaskFilter.getHideCompleted()
        communicationTaskFilter.mapHideCompleted(!hideCompleted)
    }

    fun observeHideCompleted(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationTaskFilter.observeHideCompleted(owner, observer)
    }

    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationTaskFilter.observeCloseScreen(owner, observer)
    }

    private fun closeScreen() {
       communicationTaskFilter.mapCloseScreen(true)
    }
}