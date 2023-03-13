package com.example.todoapp.datasource.auth.cloud.mappers

import com.example.todoapp.datasource.auth.cloud.models.UserCloud
import com.example.todoapp.models.UserData
import javax.inject.Inject

class UserCloudToDataMappers @Inject constructor(){
    fun transform(user: UserCloud): UserData {
        return UserData(
            localId = user.localId,
            email = user.email,
            idToken = user.idToken,
            refreshToken = user.refreshToken,
            expiresIn = user.expiresIn
        )
    }
}