package com.example.todoapp.datasource.auth.cache

import com.example.todoapp.datasource.auth.cache.mappers.UserCacheToDataMapper
import com.example.todoapp.datasource.auth.cache.mappers.UserDataToCacheMapper
import com.example.todoapp.di.DataScope
import com.example.todoapp.models.UserData
import com.example.todoapp.repository.auth.AuthCacheDataSource
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

    override suspend fun getCurrentUserId(): String {
        return currentUserDataStore.getUserId()
    }

    override suspend fun saveCurrentUserId(id: String) {
        currentUserDataStore.saveUserId(id)
    }

    override suspend fun clearCurrentUserId() {
        currentUserDataStore.clearUserId()
    }

}

