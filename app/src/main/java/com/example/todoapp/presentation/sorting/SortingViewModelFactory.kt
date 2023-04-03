package com.example.todoapp.presentation.sorting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.usecase.settings.GetTasksSortingUseCase
import com.example.todoapp.usecase.settings.SaveTasksSortingUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SortingViewModelFactory @AssistedInject constructor(
    @Assisted("communication") private val communicationSorting: CommunicationSorting,
    private val getTasksSortingUseCase: GetTasksSortingUseCase,
    private val saveTasksSortingUseCase: SaveTasksSortingUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SortingViewModel(
            communicationSorting,
            getTasksSortingUseCase,
            saveTasksSortingUseCase
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("communication") communicationSorting: CommunicationSorting,
        ): SortingViewModelFactory
    }

}