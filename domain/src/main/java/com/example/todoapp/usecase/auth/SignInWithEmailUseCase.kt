package com.example.todoapp.usecase.auth

import com.example.todoapp.di.DomainScope
import com.example.todoapp.exception.auth.AuthDomainException
import com.example.todoapp.exception.HandleError
import com.example.todoapp.exception.auth.AuthHandleRequest
import com.example.todoapp.models.AuthResult
import com.example.todoapp.models.UserDomainParams
import com.example.todoapp.repository.AuthRepository
import javax.inject.Inject

@DomainScope
class SignInWithEmailUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val handleRequest: AuthHandleRequest
) {
    suspend operator fun invoke(userParams: UserDomainParams): AuthResult = handleRequest.handle {
        repository.signInWithEmail(userParams)
    }
//        try {
//        val userDomain = repository.signInWithEmail(userParams)
//        AuthResult.Success(userDomain)
//    } catch (e: AuthDomainException) {
//        AuthResult.Failure(e)
//    }
}
