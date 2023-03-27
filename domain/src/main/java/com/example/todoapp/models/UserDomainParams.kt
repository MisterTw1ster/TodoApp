package com.example.todoapp.models

data class UserDomainParams(
    val email: String,
    val password: String
) {

    fun checkAuth(): AuthResult? {
        if (email.isEmpty()) {
            return AuthResult.LoginError("заполните логин")
        } else if (password.isEmpty()) {
            return AuthResult.PasswordError("заполните пароль")
        }
        return null
    }

}
