package com.example.core_users_data_source.cloud.mappers

import com.example.core_users_repository.models.UserData
import com.example.core_users_data_source.cloud.models.UserCloud
import dagger.Reusable
import javax.inject.Inject

@Reusable
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