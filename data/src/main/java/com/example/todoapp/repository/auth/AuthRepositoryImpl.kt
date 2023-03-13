package com.example.todoapp.repository.auth

import com.example.todoapp.di.DataScope
import com.example.todoapp.mappers.UserDataToDomainMapper
import com.example.todoapp.models.UserDomain
import com.example.todoapp.repository.AuthRepository
import javax.inject.Inject

@DataScope
class AuthRepositoryImpl @Inject constructor(
    private val authCacheDataSource: AuthCacheDataSource,
    private val authCloudDataSource: AuthCloudDataSource,
    private val dataToDomainMapper: UserDataToDomainMapper
) : AuthRepository {

    override suspend fun fetchLocalUsers(): List<UserDomain> {
        val usersDomain = authCacheDataSource.fetchUsers()
        return usersDomain.map { user ->
            dataToDomainMapper.transform(user)
        }
    }

    override suspend fun signUpWithEmail(email: String, password: String): String {
        val userCloud = authCloudDataSource.signUpWithEmail(email = email, password = password)
        authCacheDataSource.addUser(userCloud)
        authCacheDataSource.saveCurrentUserId(userCloud.localId)
        return userCloud.localId
    }

    override suspend fun signInWithEmail(email: String, password: String): String {
        val userCloud = authCloudDataSource.signInWithEmail(email = email, password = password)
        authCacheDataSource.addUser(userCloud)
        authCacheDataSource.saveCurrentUserId(userCloud.localId)
        return userCloud.localId
    }

    override suspend fun signOut(): Boolean {
        authCacheDataSource.clearCurrentUserId()
        return true
    }

}