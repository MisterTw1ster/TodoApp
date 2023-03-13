package com.example.todoapp.repository

import com.example.todoapp.models.UserDomain

interface AuthRepository {
    suspend fun fetchLocalUsers(): List<UserDomain>
    suspend fun signUpWithEmail(email: String, password: String): String
    suspend fun signInWithEmail(email: String, password: String): String
    suspend fun signOut(): Boolean
}