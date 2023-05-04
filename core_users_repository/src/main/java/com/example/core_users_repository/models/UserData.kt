package com.example.core_users_repository.models

data class UserData(
    val localId: String,
    val email: String,
    val idToken: String,
    val refreshToken: String,
    val expiresIn: String
)