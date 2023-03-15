package com.example.todoapp.models

sealed class AuthResult {

    data class Success(
        val user: UserDomain
    ): AuthResult()

    class Failure(val error: String): AuthResult()
}