package com.example.todoapp.usecase.auth

import com.example.todoapp.di.DomainScope
import com.example.todoapp.exception.HandleError
import com.example.todoapp.models.AuthResult
import com.example.todoapp.models.UserDomain
import com.example.todoapp.repository.AuthRepository
import javax.inject.Inject

@DomainScope
class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): UserDomain? {
        return repository.getCurrentUser()
    }
}
