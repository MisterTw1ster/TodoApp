package com.example.feature_user_auth.di

import com.example.core.navigation.Navigation
import com.example.core_domain.usecase.users.SignInWithEmailUseCase
import com.example.core_domain.usecase.users.SignUpWithEmailUseCase

interface UserAuthDeps {
    val navigation: Navigation
    val signInWithEmailUseCase: SignInWithEmailUseCase
    val signUpWithEmailUseCase: SignUpWithEmailUseCase
}