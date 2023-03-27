package com.example.todoapp.exception.auth

import com.example.todoapp.di.DomainScope
import com.example.todoapp.exception.HandleError
import com.example.todoapp.exception.NoInternetConnectionException
import com.example.todoapp.models.AuthResult
import javax.inject.Inject

@DomainScope
class AuthHandleDomainError @Inject constructor() : HandleError<AuthResult> {

    override fun handle(e: Exception) = when (e) {
        is NoInternetConnectionException -> AuthResult.Failure("No internet connection")
        is EmailNotFoundException -> AuthResult.LoginError("Email not found")
        is InvalidEmailException -> AuthResult.LoginError("Invalid email")
        is InvalidPasswordException -> AuthResult.PasswordError("Invalid password")
        is WeakPasswordException -> AuthResult.PasswordError("Weak password")
        else -> AuthResult.Failure("Service is unavailable")
    }

}