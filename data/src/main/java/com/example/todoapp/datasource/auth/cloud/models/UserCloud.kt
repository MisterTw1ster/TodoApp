package com.example.todoapp.datasource.auth.cloud.models

data class UserCloud(
    val localId: String,
    val email: String,
    val idToken: String,
    val refreshToken: String,
    val expiresIn: String
)
