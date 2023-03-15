package com.example.todoapp.usecase.auth

import com.example.todoapp.di.DomainScope
import com.example.todoapp.exception.HandleError
import com.example.todoapp.models.AuthResult
import com.example.todoapp.models.UserDomainParams
import com.example.todoapp.repository.AuthRepository
import javax.inject.Inject

@DomainScope
class SignInWithEmailUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val handleError: HandleError<String>
) {
    suspend operator fun invoke(userParams: UserDomainParams): AuthResult = try {
        val userDomain = repository.signInWithEmail(userParams)
        AuthResult.Success(userDomain)
    } catch (e: Exception) {
        AuthResult.Failure(handleError.handle(e))
    }
}
