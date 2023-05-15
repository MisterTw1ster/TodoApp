package com.example.feature_dialogs.taskSorting.viewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.settings.GetTasksSortingUseCase
import com.example.domain.usecase.settings.SaveTasksSortingUseCase
import com.example.feature_dialogs.taskSorting.view.CommunicationTaskSorting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TaskSortingViewModel(
    private val communicationTaskSorting: CommunicationTaskSorting,
    private val getTasksSortingUseCase: GetTasksSortingUseCase,
    private val saveTasksSortingUseCase: SaveTasksSortingUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch(Dispatchers.IO) {
            val sortMode = getTasksSortingUseCase()
            communicationTaskSorting.mapSortMode(sortMode)
        }
    }

    fun saveSortMode() {
        scope.launch {
            val sortMode = communicationTaskSorting.getSortMode()
            saveTasksSortingUseCase.invoke(sortMode)
        }
        closeScreen()
    }

    fun setSortMode(sortMode: String) {
        communicationTaskSorting.mapSortMode(sortMode)
    }

    fun observeSortMode(owner: LifecycleOwner, observer: Observer<String>) {
        communicationTaskSorting.observeSortMode(owner, observer)
    }

    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationTaskSorting.observeCloseScreen(owner, observer)
    }

    private fun closeScreen() {
        communicationTaskSorting.mapCloseScreen(true)
    }
}