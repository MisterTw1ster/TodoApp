package com.example.user_select.di

import com.example.core.navigation.Navigation
import com.example.domain.usecase.users.FetchUsersUseCase
import com.example.domain.usecase.users.SetCurrentUserIdUseCase

interface UserSelectDeps {
    val navigation: Navigation
    val fetchUsersUseCase: FetchUsersUseCase
    val setCurrentUserIdUseCase: SetCurrentUserIdUseCase
}