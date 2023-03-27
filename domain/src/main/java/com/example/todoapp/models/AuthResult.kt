package com.example.todoapp.models

sealed class AuthResult {

    data class Success(
        val user: UserDomain
    ) : AuthResult()

    data class LoginError(
        val error: String
    ) : AuthResult()

    data class PasswordError(
        val error: String
    ) : AuthResult()

    data class Failure(
        val error: String
    ) : AuthResult()

}