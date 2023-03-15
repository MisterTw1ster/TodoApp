package com.example.todoapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.presentation.auth.mappers.UserDomainToUIMapper
import com.example.todoapp.presentation.common.navigation.Navigation
import com.example.todoapp.usecase.*
import com.example.todoapp.usecase.auth.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AuthViewModelFactory @AssistedInject constructor(
    @Assisted("communication") private val communication: CommunicationAuth,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val setCurrentUserUseCase: SetCurrentUserIdUseCase,
    private val getUsers: FetchUsersUseCase,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
    private val domainToUIMapper: UserDomainToUIMapper
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(
            communication,
            getCurrentUserUseCase,
            setCurrentUserUseCase,
            getUsers,
            signInWithEmailUseCase,
            signUpWithEmailUseCase,
            domainToUIMapper
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("communication") communication: CommunicationAuth
        ): AuthViewModelFactory
    }

}