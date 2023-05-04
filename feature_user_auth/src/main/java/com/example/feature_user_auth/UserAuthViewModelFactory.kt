package com.example.feature_user_auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.navigation.Navigation
import com.example.core_domain.usecase.users.SignInWithEmailUseCase
import com.example.core_domain.usecase.users.SignUpWithEmailUseCase
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