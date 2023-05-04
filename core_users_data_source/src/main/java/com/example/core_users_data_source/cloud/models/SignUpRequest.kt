package com.example.core_users_data_source.cloud.models

data class SignUpRequest(
    val email: String,
    val password: String,
    private val returnSecureToken: Boolean = true
)