package com.example.todoapp.datasource.auth.cloud.mappers

import com.example.todoapp.datasource.auth.cloud.models.UserCloud
import com.example.todoapp.models.UserData
import javax.inject.Inject

class UserDataToCloudMappers @Inject constructor(){
    fun transform(user: UserData): UserCloud {
        return UserCloud(
            localId = user.localId,
            email = user.email,
            idToken = user.idToken,
            refreshToken = user.refreshToken,
            expiresIn = user.expiresIn
        )
    }
}