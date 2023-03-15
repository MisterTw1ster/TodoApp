package com.example.todoapp.repository

import com.example.todoapp.models.UserDomain
import com.example.todoapp.models.UserDomainParams
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun fetchLocalUsers(): List<UserDomain>
    suspend fun observeCurrentUser(): Flow<UserDomain?>
    suspend fun getCurrentUser(): UserDomain?
    suspend fun setCurrentUserId(userId: String): Boolean
    suspend fun signUpWithEmail(userParams: UserDomainParams): UserDomain
    suspend fun signInWithEmail(userParams: UserDomainParams): UserDomain
    suspend fun signOut(): Boolean
}