package com.example.todoapp.datasource.auth.cloud.models

data class SignUpRequest(
    val email: String,
    val password: String,
    private val returnSecureToken: Boolean = true
)