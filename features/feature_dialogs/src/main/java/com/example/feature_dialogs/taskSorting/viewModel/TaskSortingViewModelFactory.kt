package com.example.feature_dialogs.taskSorting.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.settings.GetTasksSortingUseCase
import com.example.domain.usecase.settings.SaveTasksSortingUseCase
import com.example.feature_dialogs.taskSorting.view.CommunicationTaskSorting
import javax.inject.Inject

class TaskSortingViewModelFactory @Inject constructor(
    private val communicationTaskSorting: CommunicationTaskSorting,
    private val getTasksSortingUseCase: GetTasksSortingUseCase,
    private val saveTasksSortingUseCase: SaveTasksSortingUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskSortingViewModel(
            communicationTaskSorting,
            getTasksSortingUseCase,
            saveTasksSortingUseCase
        ) as T
    }

}