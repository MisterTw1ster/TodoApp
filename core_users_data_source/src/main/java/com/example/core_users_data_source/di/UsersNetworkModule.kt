package com.example.core_users_data_source.di

import com.example.core.di.qualifier.Users
import com.example.core.di.scope.AppScope
import com.example.core_users_data_source.cloud.UsersService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class UsersNetworkModule {
    @Provides
    @AppScope
    @Users
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @AppScope
    @Users
    fun provideOkHttpClient(
        @Users httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @AppScope
    @Users
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @AppScope
    @Users
    fun providesRetrofit(
        @Users okHttpClient: OkHttpClient,
        @Users gsonConverter: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_USERS)
            .client(okHttpClient)
            .addConverterFactory(gsonConverter)
            .build()
    }

    @Provides
    @AppScope
    fun providesUsersService(@Users retrofit: Retrofit): UsersService {
        return retrofit.create(UsersService::class.java)
    }

    companion object {
        const val BASE_URL_USERS = "https://identitytoolkit.googleapis.com/v1/"
    }
}