package com.example.core_task_data_source.di

import com.example.core.di.qualifier.Tasks
import com.example.core.di.scope.AppScope
import com.example.core_task_data_source.cloud.TasksService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class TasksNetworkModule {
    @Provides
    @AppScope
    @Tasks
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @AppScope
    @Tasks
    fun provideOkHttpClient(
        @Tasks httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @AppScope
    @Tasks
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @AppScope
    @Tasks
    fun providesRetrofit(
        @Tasks okHttpClient: OkHttpClient,
        @Tasks gsonConverter: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_TASKS)
            .client(okHttpClient)
            .addConverterFactory(gsonConverter)
            .build()
    }

    @Provides
    @AppScope
    fun providesTasksService(@Tasks retrofit: Retrofit): TasksService {
        return retrofit.create(TasksService::class.java)
    }

    companion object {
        const val BASE_URL_TASKS =
            "https://todolistandtasks-74fd1-default-rtdb.europe-west1.firebasedatabase.app/"
    }
}