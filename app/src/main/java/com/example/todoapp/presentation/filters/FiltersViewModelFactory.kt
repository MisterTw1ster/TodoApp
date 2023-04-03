package com.example.todoapp.presentation.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.usecase.settings.FetchFiltersUseCase
import com.example.todoapp.usecase.settings.SaveSettingHideCompletedUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FiltersViewModelFactory @AssistedInject constructor(
    @Assisted("communication") private val communicationFilters: CommunicationFilters,
    private val fetchTasksFiltersUseCase: FetchFiltersUseCase,
    private val saveSettingHideCompletedUseCase: SaveSettingHideCompletedUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FiltersViewModel(
            communicationFilters,
            fetchTasksFiltersUseCase,
            saveSettingHideCompletedUseCase
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("communication") communicationFilters: CommunicationFilters,
        ): FiltersViewModelFactory
    }

}