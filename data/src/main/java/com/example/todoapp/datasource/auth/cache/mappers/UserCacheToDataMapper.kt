package com.example.todoapp.datasource.auth.cache.mappers

import com.example.todoapp.datasource.auth.cache.UserCache
import com.example.todoapp.models.UserData
import javax.inject.Inject

class UserCacheToDataMapper @Inject constructor(){
    fun transform(user: UserCache): UserData {
        return UserData(
            localId = user.localId,
            email = user.email,
            idToken = user.idToken,
            refreshToken = user.refreshToken,
            expiresIn = user.expiresIn
        )
    }
}