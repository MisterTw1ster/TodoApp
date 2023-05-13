package com.example.feature_dialogs.taskFilter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_domain.usecase.settings.FetchFiltersUseCase
import com.example.core_domain.usecase.settings.SaveHideCompletedFiltersUseCase
import com.example.feature_dialogs.taskFilter.view.CommunicationTaskFilter
import javax.inject.Inject

class TaskFilterViewModelFactory @Inject constructor(
    private val communicationTaskFilter: CommunicationTaskFilter,
    private val fetchTasksFiltersUseCase: FetchFiltersUseCase,
    private val saveHideCompletedFiltersUseCase: SaveHideCompletedFiltersUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskFilterViewModel(
            communicationTaskFilter,
            fetchTasksFiltersUseCase,
            saveHideCompletedFiltersUseCase
        ) as T
    }

}