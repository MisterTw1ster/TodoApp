package com.example.core_domain.repository

import com.example.core_domain.models.UserDomain
import com.example.core_domain.models.UserDomainParams
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun fetchLocalUsers(): List<UserDomain>
    suspend fun observeCurrentUser(): Flow<UserDomain?>
    suspend fun getCurrentUser(): UserDomain?
    suspend fun setCurrentUserId(userId: String): Boolean
    suspend fun signUpWithEmail(userParams: UserDomainParams): UserDomain
    suspend fun signInWithEmail(userParams: UserDomainParams): UserDomain
    suspend fun signOut(): Boolean
}