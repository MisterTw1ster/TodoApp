package com.example.core_domain.exception.users

import com.example.core_domain.models.UserDomain

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