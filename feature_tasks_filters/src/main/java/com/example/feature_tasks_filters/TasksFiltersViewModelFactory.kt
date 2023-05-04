package com.example.feature_tasks_filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_domain.usecase.settings.FetchFiltersUseCase
import com.example.core_domain.usecase.settings.SaveHideCompletedFiltersUseCase
import javax.inject.Inject

class TasksFiltersViewModelFactory @Inject constructor(
    private val communicationTasksFilters: CommunicationTasksFilters,
    private val fetchTasksFiltersUseCase: FetchFiltersUseCase,
    private val saveHideCompletedFiltersUseCase: SaveHideCompletedFiltersUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksFiltersViewModel(
            communicationTasksFilters,
            fetchTasksFiltersUseCase,
            saveHideCompletedFiltersUseCase
        ) as T
    }

}