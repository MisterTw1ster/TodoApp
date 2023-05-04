package com.example.feature_user_select.di

import com.example.core.navigation.Navigation
import com.example.core_domain.usecase.users.FetchUsersUseCase
import com.example.core_domain.usecase.users.SetCurrentUserIdUseCase

interface UserSelectDeps {
    val navigation: Navigation
    val fetchUsersUseCase: FetchUsersUseCase
    val setCurrentUserIdUseCase: SetCurrentUserIdUseCase
}