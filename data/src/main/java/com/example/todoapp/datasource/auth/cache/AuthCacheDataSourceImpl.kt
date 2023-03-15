package com.example.todoapp.datasource.auth.cache

import com.example.todoapp.datasource.auth.cache.mappers.UserCacheToDataMapper
import com.example.todoapp.datasource.auth.cache.mappers.UserDataToCacheMapper
import com.example.todoapp.di.DataScope
import com.example.todoapp.models.UserData
import com.example.todoapp.repository.auth.AuthCacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@DataScope
class AuthCacheDataSourceImpl @Inject constructor(
    private val dao: UsersDao,
    private val currentUserDataStore: CurrentUserDataStore,
    private val cacheToDataMapper: UserCacheToDataMapper,
    private val dataToCacheMapper: UserDataToCacheMapper
) : AuthCacheDataSource {

    override suspend fun fetchUsers(): List<UserData> {
        return dao.fetchUsers().map { user ->
            cacheToDataMapper.transform(user)
        }
    }

    override suspend fun addUser(user: UserData) {
        val userCache = dataToCacheMapper.transform(user)
        dao.addUser(userCache)
    }

    override suspend fun editUser(user: UserData) {
        val userCache = dataToCacheMapper.transform(user)
        dao.editUser(userCache)
    }

    override suspend fun observeCurrentUser(): Flow<UserData?> {
        return currentUserDataStore.observeUserId().map { userId ->
            userId.takeIf { userId != CurrentUserDataStore.UNKNOWN_USER }?.let {
                val userCache = dao.getUser(userId)
                cacheToDataMapper.transform(userCache)
            }
        }
    }

    override suspend fun getCurrentUser(): UserData? {
        val currentUserId = currentUserDataStore.getUserId()
        if (currentUserId == CurrentUserDataStore.UNKNOWN_USER) return null
        val userCache = dao.getUser(currentUserId)
        return cacheToDataMapper.transform(userCache)
    }

    override suspend fun setCurrentUserId(userId: String) {
        currentUserDataStore.setUserId(userId)
    }

    override suspend fun clearCurrentUserId() {
        currentUserDataStore.clearUserId()
    }

}

