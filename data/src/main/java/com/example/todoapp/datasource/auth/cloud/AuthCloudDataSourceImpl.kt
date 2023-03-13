package com.example.todoapp.datasource.auth.cloud

import com.example.todoapp.datasource.auth.cloud.mappers.UserCloudToDataMappers
import com.example.todoapp.datasource.auth.cloud.models.SignInRequest
import com.example.todoapp.datasource.auth.cloud.models.SignUpRequest
import com.example.todoapp.di.DataScope
import com.example.todoapp.models.UserData
import com.example.todoapp.repository.auth.AuthCloudDataSource
import javax.inject.Inject

@DataScope
class AuthCloudDataSourceImpl @Inject constructor(
    private val api: AuthService,
    private val userCloudToDataMappers: UserCloudToDataMappers
) : AuthCloudDataSource {

    override suspend fun signUpWithEmail(email: String, password: String): UserData {
        val signUpRequest = SignUpRequest(email = email, password = password)
        val userCloud = api.singUpWithEmail(signUpRequest)
        return userCloudToDataMappers.transform(userCloud)
    }

    override suspend fun signInWithEmail(email: String, password: String): UserData {
        val signInRequest = SignInRequest(email = email, password = password)
        val userCloud = api.singInWithEmail(signInRequest)
        return userCloudToDataMappers.transform(userCloud)
    }

}

