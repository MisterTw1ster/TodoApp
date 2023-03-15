package com.example.todoapp.repository.auth

import com.example.todoapp.models.UserData
import kotlinx.coroutines.flow.Flow

interface AuthCacheDataSource {
    suspend fun fetchUsers(): List<UserData>
    suspend fun addUser(user: UserData)
    suspend fun editUser(user: UserData)
    suspend fun observeCurrentUser(): Flow<UserData?>
    suspend fun getCurrentUser(): UserData?
    suspend fun setCurrentUserId(userId: String)
    suspend fun clearCurrentUserId()
}