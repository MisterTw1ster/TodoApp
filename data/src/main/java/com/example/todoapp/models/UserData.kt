package com.example.todoapp.models

data class UserData(
    val localId: String,
    val email: String,
    val idToken: String,
    val refreshToken: String,
    val expiresIn: String
)