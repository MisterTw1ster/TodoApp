package com.example.todoapp.repository.auth

import com.example.todoapp.di.DataScope
import com.example.todoapp.exception.HandleError
import com.example.todoapp.mappers.UserDataToDomainMapper
import com.example.todoapp.models.UserDomain
import com.example.todoapp.models.UserDomainParams
import com.example.todoapp.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@DataScope
class AuthRepositoryImpl @Inject constructor(
    private val authCacheDataSource: AuthCacheDataSource,
    private val authCloudDataSource: AuthCloudDataSource,
    private val dataToDomainMapper: UserDataToDomainMapper,
    private val handleError: HandleError<Exception>
) : AuthRepository {

    override suspend fun fetchLocalUsers(): List<UserDomain> {
        val usersDomain = authCacheDataSource.fetchUsers()
        return usersDomain.map { user ->
            dataToDomainMapper.transform(user)
        }
    }

    override suspend fun observeCurrentUser(): Flow<UserDomain?> {
        return authCacheDataSource.observeCurrentUser().map { user ->
            user?.let { dataToDomainMapper.transform(user) }
        }
    }

    override suspend fun getCurrentUser(): UserDomain? {
        val userData = authCacheDataSource.getCurrentUser()
        return userData?.let { dataToDomainMapper.transform(it) }
    }

    override suspend fun setCurrentUserId(userId: String): Boolean {
        authCacheDataSource.setCurrentUserId(userId)
        return true
    }

    override suspend fun signUpWithEmail(userParams: UserDomainParams): UserDomain {
        try {
            val userData = authCloudDataSource.signUpWithEmail(userParams.email, userParams.password)
            authCacheDataSource.addUser(userData)
            authCacheDataSource.setCurrentUserId(userData.localId)
            return dataToDomainMapper.transform(userData)
        } catch (e: Exception) {
            throw handleError.handle(e)
        }
    }

    override suspend fun signInWithEmail(userParams: UserDomainParams): UserDomain {
        try {
            val userData =
                authCloudDataSource.signInWithEmail(userParams.email, userParams.password)
            authCacheDataSource.addUser(userData)
            authCacheDataSource.setCurrentUserId(userData.localId)
            return dataToDomainMapper.transform(userData)
        } catch (e: Exception) {
            throw handleError.handle(e)
        }
    }

    override suspend fun signOut(): Boolean {
        authCacheDataSource.clearCurrentUserId()
        return true
    }

}