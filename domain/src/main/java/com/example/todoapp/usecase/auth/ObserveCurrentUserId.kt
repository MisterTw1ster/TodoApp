package com.example.todoapp.usecase.auth

import com.example.todoapp.di.DomainScope
import com.example.todoapp.repository.AuthRepository
import javax.inject.Inject

@DomainScope
class ObserveCurrentUserId @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.observeCurrentUser()
}