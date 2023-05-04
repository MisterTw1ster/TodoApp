package com.example.core_users_data_source.cloud.mappers

import com.example.core_users_repository.models.UserData
import com.example.core_users_data_source.cloud.models.UserCloud
import dagger.Reusable
import javax.inject.Inject

@Reusable
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