package com.example.user_auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.navigation.Navigation
import com.example.domain.usecase.users.SignInWithEmailUseCase
import com.example.domain.usecase.users.SignUpWithEmailUseCase
import com.example.user_auth.view.CommunicationUserAuth
import javax.inject.Inject

class UserAuthViewModelFactory @Inject constructor(
    private val communication: CommunicationUserAuth,
    private val navigation: Navigation,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserAuthViewModel(
            communication,
            navigation,
            signInWithEmailUseCase,
            signUpWithEmailUseCase
        ) as T
    }

}