package com.example.feature_tasks_sorting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_domain.usecase.settings.GetTasksSortingUseCase
import com.example.core_domain.usecase.settings.SaveTasksSortingUseCase
import javax.inject.Inject

class TasksSortingViewModelFactory @Inject constructor(
    private val communicationTasksSorting: CommunicationTasksSorting,
    private val getTasksSortingUseCase: GetTasksSortingUseCase,
    private val saveTasksSortingUseCase: SaveTasksSortingUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksSortingViewModel(
            communicationTasksSorting,
            getTasksSortingUseCase,
            saveTasksSortingUseCase
        ) as T
    }

}