package com.example.todoapp.datasource.auth.cloud

import com.example.data.BuildConfig
import com.example.todoapp.datasource.auth.cloud.models.SignInRequest
import com.example.todoapp.datasource.auth.cloud.models.SignUpRequest
import com.example.todoapp.datasource.auth.cloud.models.UserCloud
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("accounts:signUp")
    suspend fun singUpWithEmail(
        @Body request: SignUpRequest,
        @Query("key") key: String = BuildConfig.AUTH_API_KEY
    ): UserCloud

    @POST("accounts:signInWithPassword")
    suspend fun singInWithEmail(
        @Body request: SignInRequest,
        @Query("key") key: String = BuildConfig.AUTH_API_KEY
    ): UserCloud

}