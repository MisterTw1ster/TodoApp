package com.example.todoapp.presentation.sorting

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.usecase.settings.GetTasksSortingUseCase
import com.example.todoapp.usecase.settings.SaveTasksSortingUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SortingViewModel(
    private val communicationSorting: CommunicationSorting,
    private val getTasksSortingUseCase: GetTasksSortingUseCase,
    private val saveTasksSortingUseCase: SaveTasksSortingUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch(Dispatchers.IO) {
            val sortMode = getTasksSortingUseCase()
            communicationSorting.mapSortMode(sortMode)
        }
    }

    fun saveSortMode() {
        scope.launch {
            val sortMode = communicationSorting.getSortMode()
            saveTasksSortingUseCase.invoke(sortMode)
        }
        closeScreen()
    }

    fun setSortMode(sortMode: String) {
        communicationSorting.mapSortMode(sortMode)
    }

    fun observeSortMode(owner: LifecycleOwner, observer: Observer<String>) {
        communicationSorting.observeSortMode(owner, observer)
    }

    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
    communicationSorting.observeCloseScreen(owner, observer)
    }

    private fun closeScreen() {
        communicationSorting.mapCloseScreen(true)
    }
}