package com.example.core_users_repository

import com.example.core_users_repository.models.UserData

interface UsersCloudDataSource {
    suspend fun signUpWithEmail(email: String, password: String): UserData
    suspend fun signInWithEmail(email: String, password: String): UserData
}