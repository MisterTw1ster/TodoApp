package com.example.todoapp.datasource.auth.cloud.models

data class SignInRequest(
    val email: String,
    val password: String,
    private val returnSecureToken: Boolean = true
)