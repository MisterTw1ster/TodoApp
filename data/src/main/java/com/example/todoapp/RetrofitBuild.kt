package com.example.todoapp

import com.example.todoapp.datasource.tasks.cloud.TasksService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuild {

//    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    }
//
//    fun provideOkHttpClient(): OkHttpClient {
//        val httpLoggingInterceptor = provideHttpLoggingInterceptor()
//        return OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }
//
//    fun providesRetrofit(): Retrofit {
//        val okHttpClient = provideOkHttpClient()
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    fun providesApiService(): TasksService {
//        val retrofit = providesRetrofit()
//        return retrofit.create(TasksService::class.java)
//    }
//
//    companion object {
//        const val BASE_URL = "https://todolistandtasks-74fd1-default-rtdb.europe-west1.firebasedatabase.app/"
//    }
}