package com.example.todoapp.usecase.auth

import com.example.todoapp.di.DomainScope
import com.example.todoapp.repository.AuthRepository
import javax.inject.Inject

@DomainScope
class SignInWithEmailUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): String {
        return repository.signInWithEmail(email = email, password = password)
    }
}