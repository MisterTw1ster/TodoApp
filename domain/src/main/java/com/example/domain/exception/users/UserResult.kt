package com.example.domain.exception.users

import com.example.domain.models.UserDomain

sealed class UserResult {

    data class Success(
        val user: UserDomain
    ) : UserResult()

    data class LoginError(
        val error: String
    ) : UserResult()

    data class PasswordError(
        val error: String
    ) : UserResult()

    data class Failure(
        val error: String
    ) : UserResult()

}