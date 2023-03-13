package com.example.todoapp.usecase.auth

import com.example.todoapp.di.DomainScope
import com.example.todoapp.models.UserDomain
import com.example.todoapp.repository.AuthRepository
import javax.inject.Inject

@DomainScope
class FetchUsersUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): List<UserDomain> {
        return repository.fetchLocalUsers()
    }
}