package com.example.todoapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.usecase.*
import com.example.todoapp.usecase.auth.FetchUsersUseCase
import com.example.todoapp.usecase.auth.SignInWithEmailUseCase
import com.example.todoapp.usecase.auth.SignUpWithEmailUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AuthViewModelFactory @AssistedInject constructor(
    @Assisted("communication") private val communication: CommunicationAuth,
    private val getUsers: FetchUsersUseCase,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(
            communication,
            getUsers,
            signInWithEmailUseCase,
            signUpWithEmailUseCase
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("communication") communication: CommunicationAuth
        ): AuthViewModelFactory
    }

}