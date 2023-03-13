package com.example.todoapp.repository.auth

import com.example.todoapp.models.UserData

interface AuthCloudDataSource {
    suspend fun signUpWithEmail(email: String, password: String): UserData
    suspend fun signInWithEmail(email: String, password: String): UserData
}