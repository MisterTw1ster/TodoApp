package com.example.todoapp.presentation.filters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.usecase.settings.FetchFiltersUseCase
import com.example.todoapp.usecase.settings.SaveHideCompletedFiltersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FiltersViewModel(
    private val communicationFilters: CommunicationFilters,
    private val fetchTasksFiltersUseCase: FetchFiltersUseCase,
    private val saveHideCompletedFiltersUseCase: SaveHideCompletedFiltersUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch(Dispatchers.IO) {
            val filters = fetchTasksFiltersUseCase()
            communicationFilters.mapHideCompleted(filters.hideCompleted)
        }
    }

    fun saveFilters() {
        scope.launch {
            val saveParams = communicationFilters.getSaveParams()
            saveHideCompletedFiltersUseCase(saveParams.hideCompleted)
        }
        closeScreen()
    }

    fun setHideCompleted() {
        val hideCompleted = communicationFilters.getHideCompleted()
        communicationFilters.mapHideCompleted(!hideCompleted)
    }

    fun observeHideCompleted(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationFilters.observeHideCompleted(owner, observer)
    }

    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationFilters.observeCloseScreen(owner, observer)
    }

    private fun closeScreen() {
       communicationFilters.mapCloseScreen(true)
    }
}