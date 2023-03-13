package com.example.todoapp.repository.auth

import com.example.todoapp.models.UserData

interface AuthCacheDataSource {
    suspend fun fetchUsers(): List<UserData>
    suspend fun addUser(user: UserData)
    suspend fun editUser(user: UserData)
    suspend fun getCurrentUserId(): String?
    suspend fun saveCurrentUserId(id: String)
    suspend fun clearCurrentUserId()
}