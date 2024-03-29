package com.example.todoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.presentation.navigation.CommunicationNavigation
import com.example.todoapp.presentation.navigation.NavigationImpl
import com.example.domain.usecase.users.FetchUsersUseCase
import com.example.domain.usecase.users.GetCurrentUserUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val navigationImpl: NavigationImpl,
    private val communicationNavigation: CommunicationNavigation,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val fetchUsersUseCase: FetchUsersUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            navigationImpl,
            communicationNavigation,
            getCurrentUserUseCase,
            fetchUsersUseCase
        ) as T
    }

}