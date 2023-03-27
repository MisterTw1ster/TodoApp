package com.example.todoapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.presentation.common.navigation.newnav.NavigationCommunication
import com.example.todoapp.usecase.*
import com.example.todoapp.usecase.auth.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AuthViewModelFactory @AssistedInject constructor(
    @Assisted("communication") private val communication: AuthCommunication,
    @Assisted("navigationCommunication") private val navigationCommunication: NavigationCommunication.Base,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(
            communication,
            navigationCommunication,
            signInWithEmailUseCase,
            signUpWithEmailUseCase
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("communication") communication: AuthCommunication,
            @Assisted("navigationCommunication") navigationCommunication: NavigationCommunication.Base,
        ): AuthViewModelFactory
    }

}