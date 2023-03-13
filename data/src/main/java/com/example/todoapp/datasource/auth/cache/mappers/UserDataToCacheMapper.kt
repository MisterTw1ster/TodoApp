package com.example.todoapp.datasource.auth.cache.mappers

import com.example.todoapp.datasource.auth.cache.UserCache
import com.example.todoapp.models.UserData
import javax.inject.Inject

class UserDataToCacheMapper @Inject constructor(){
    fun transform(user: UserData):  UserCache{
        return UserCache(
            localId = user.localId,
            email = user.email,
            idToken = user.idToken,
            refreshToken = user.refreshToken,
            expiresIn = user.expiresIn
        )
    }
}