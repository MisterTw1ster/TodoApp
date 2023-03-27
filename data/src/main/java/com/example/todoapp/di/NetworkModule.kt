package com.example.todoapp.di

import com.example.todoapp.datasource.auth.cloud.AuthService
import com.example.todoapp.datasource.tasks.cloud.TasksService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

//    @Provides
//    @DataScope
//    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    }
//
//    @Provides
//    @DataScope
//    fun provideOkHttpClient(
//        httpLoggingInterceptor: HttpLoggingInterceptor
//    ): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }

//    @Provides
//    @DataScope
//    fun providesRetrofit(): Retrofit {
//        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

    @Provides
//    @DataScope
    fun providesTasksService(): TasksService {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
         val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_TASKS)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(TasksService::class.java)
    }

    @Provides
//    @DataScope
    fun providesAuthService(): AuthService {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_AUTH)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
        return retrofit.create(AuthService::class.java)
    }

    companion object {
        const val BASE_URL_TASKS = "https://todolistandtasks-74fd1-default-rtdb.europe-west1.firebasedatabase.app/"
        const val BASE_URL_AUTH = "https://identitytoolkit.googleapis.com/v1/"
    }
}