package com.example.todoapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.presentation.auth.mappers.UserDomainToUIMapper
import com.example.todoapp.presentation.common.navigation.newnav.NavigationCommunication
import com.example.todoapp.usecase.*
import com.example.todoapp.usecase.auth.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SelectUserViewModelFactory @AssistedInject constructor(
    @Assisted("communication") private val communication: SelectUserCommunication,
    @Assisted("navigationCommunication") private val navigationCommunication: NavigationCommunication.Base,
    private val setCurrentUserUseCase: SetCurrentUserIdUseCase,
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val domainToUIMapper: UserDomainToUIMapper
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SelectUserViewModel(
            communication,
            navigationCommunication,
            setCurrentUserUseCase,
            fetchUsersUseCase,
            domainToUIMapper
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("communication") communication: SelectUserCommunication,
            @Assisted("navigationCommunication") navigationCommunication: NavigationCommunication.Base
        ): SelectUserViewModelFactory
    }

}