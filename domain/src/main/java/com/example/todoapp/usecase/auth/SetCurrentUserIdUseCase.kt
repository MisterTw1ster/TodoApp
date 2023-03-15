package com.example.todoapp.usecase.auth

import com.example.todoapp.di.DomainScope
import com.example.todoapp.repository.AuthRepository
import javax.inject.Inject

@DomainScope
class SetCurrentUserIdUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(userId: String): Boolean {
        return repository.setCurrentUserId(userId)
    }
}
