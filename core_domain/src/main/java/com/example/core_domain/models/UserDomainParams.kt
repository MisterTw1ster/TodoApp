package com.example.core_domain.models

import com.example.core_domain.exception.users.UserResult

data class UserDomainParams(
    val email: String,
    val password: String
) {

    fun checkAuth(): UserResult? {
        if (email.isEmpty()) {
            return UserResult.LoginError("заполните логин")
        } else if (password.isEmpty()) {
            return UserResult.PasswordError("заполните пароль")
        }
        return null
    }

}
