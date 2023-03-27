package com.example.todoapp.exception.auth

import com.example.todoapp.di.DomainScope
import com.example.todoapp.models.AuthResult
import com.example.todoapp.models.UserDomain
import javax.inject.Inject

@DomainScope
class AuthHandleRequest  @Inject constructor(
    private val handleError: AuthHandleDomainError
)  {

    suspend fun handle(block: suspend () -> UserDomain): AuthResult = try {
        val userDomain = block.invoke()
        AuthResult.Success(userDomain)
    } catch (e: Exception) {
        handleError.handle(e)
    }
}