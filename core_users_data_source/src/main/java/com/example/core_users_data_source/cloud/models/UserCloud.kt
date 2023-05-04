package com.example.core_users_data_source.cloud.models

data class UserCloud(
    val localId: String,
    val email: String,
    val idToken: String,
    val refreshToken: String,
    val expiresIn: String
)
