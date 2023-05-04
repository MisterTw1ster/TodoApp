package com.example.feature_tasks_sorting

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_domain.usecase.settings.GetTasksSortingUseCase
import com.example.core_domain.usecase.settings.SaveTasksSortingUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TasksSortingViewModel(
    private val communicationTasksSorting: CommunicationTasksSorting,
    private val getTasksSortingUseCase: GetTasksSortingUseCase,
    private val saveTasksSortingUseCase: SaveTasksSortingUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch(Dispatchers.IO) {
            val sortMode = getTasksSortingUseCase()
            communicationTasksSorting.mapSortMode(sortMode)
        }
    }

    fun saveSortMode() {
        scope.launch {
            val sortMode = communicationTasksSorting.getSortMode()
            saveTasksSortingUseCase.invoke(sortMode)
        }
        closeScreen()
    }

    fun setSortMode(sortMode: String) {
        communicationTasksSorting.mapSortMode(sortMode)
    }

    fun observeSortMode(owner: LifecycleOwner, observer: Observer<String>) {
        communicationTasksSorting.observeSortMode(owner, observer)
    }

    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationTasksSorting.observeCloseScreen(owner, observer)
    }

    private fun closeScreen() {
        communicationTasksSorting.mapCloseScreen(true)
    }
}