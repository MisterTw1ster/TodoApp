package com.example.todoapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.presentation.common.navigation.newnav.NavigationCommunication
import com.example.todoapp.usecase.auth.FetchUsersUseCase
import com.example.todoapp.usecase.auth.GetCurrentUserUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MainViewModelFactory @AssistedInject constructor(
    @Assisted("navigationCommunication") private val navigationCommunication: NavigationCommunication.Base,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val fetchUsersUseCase: FetchUsersUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            navigationCommunication,
            getCurrentUserUseCase,
            fetchUsersUseCase
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("navigationCommunication") navigationCommunication: NavigationCommunication.Base
        ): MainViewModelFactory
    }
}