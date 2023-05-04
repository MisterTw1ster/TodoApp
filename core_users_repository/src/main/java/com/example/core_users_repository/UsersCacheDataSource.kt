package com.example.core_users_repository

import com.example.core_users_repository.models.UserData
import kotlinx.coroutines.flow.Flow

interface UsersCacheDataSource {
    suspend fun fetchUsers(): List<UserData>
    suspend fun addUser(user: UserData)
    suspend fun editUser(user: UserData)
    suspend fun observeCurrentUser(): Flow<UserData?>
    suspend fun getCurrentUser(): UserData?
    suspend fun setCurrentUserId(userId: String)
    suspend fun clearCurrentUserId()
}