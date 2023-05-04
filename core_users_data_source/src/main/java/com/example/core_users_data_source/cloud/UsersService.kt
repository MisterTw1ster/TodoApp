package com.example.core_users_data_source.cloud

import com.example.core_users_data_source.BuildConfig
import com.example.core_users_data_source.cloud.models.SignInRequest
import com.example.core_users_data_source.cloud.models.SignUpRequest
import com.example.core_users_data_source.cloud.models.UserCloud
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface UsersService {

    @POST("./accounts:signUp")
    suspend fun singUpWithEmail(
        @Body request: SignUpRequest,
        @Query("key") key: String = BuildConfig.AUTH_API_KEY
    ): UserCloud

    @POST("./accounts:signInWithPassword")
    suspend fun singInWithEmail(
        @Body request: SignInRequest,
        @Query("key") key: String = BuildConfig.AUTH_API_KEY
    ):  Response<UserCloud>

}