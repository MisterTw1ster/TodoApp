package com.example.core_users_data_source.cache.mappers

import com.example.core_users_repository.models.UserData
import com.example.core_users_data_source.cache.models.UserCache
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UserDataToCacheMapper @Inject constructor(){
    fun transform(user: UserData): UserCache {
        return UserCache(
            localId = user.localId,
            email = user.email,
            idToken = user.idToken,
            refreshToken = user.refreshToken,
            expiresIn = user.expiresIn
        )
    }
}