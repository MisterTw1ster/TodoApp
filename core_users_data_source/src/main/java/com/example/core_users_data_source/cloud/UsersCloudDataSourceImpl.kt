package com.example.core_users_data_source.cloud

import com.example.core.di.scope.AppScope
import com.example.core_users_data_source.cloud.mappers.UserCloudToDataMappers
import com.example.core_users_data_source.cloud.models.SignInRequest
import com.example.core_users_data_source.cloud.models.SignUpRequest
import com.example.core_users_repository.UsersCloudDataSource
import com.example.core_users_repository.models.UserData
import org.json.JSONObject
import javax.inject.Inject

@AppScope
class UsersCloudDataSourceImpl @Inject constructor(
    private val api: UsersService,
    private val userCloudToDataMappers: UserCloudToDataMappers
) : UsersCloudDataSource {

    override suspend fun signUpWithEmail(email: String, password: String): UserData {
        val signUpRequest = SignUpRequest(email = email, password = password)
        val userCloud = api.singUpWithEmail(signUpRequest)
        return userCloudToDataMappers.transform(userCloud)
    }

    override suspend fun signInWithEmail(email: String, password: String): UserData {
        val signInRequest = SignInRequest(email = email, password = password)
        val response = api.singInWithEmail(signInRequest)
        return if (response.code() == 200 && response.body() != null) {
            val userCloud = response.body()!!
           userCloudToDataMappers.transform(userCloud)
        } else {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            val message = (errorObj["error"] as JSONObject).getString("message")
            throw IllegalArgumentException(message)
        }

    }

}

