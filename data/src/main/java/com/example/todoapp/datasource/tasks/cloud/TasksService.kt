package com.example.todoapp.datasource.tasks.cloud

import retrofit2.http.*

interface TasksService {

    @GET("me.json")
    suspend fun fetchTasks(): List<TaskCloud>

    @POST("me/tasks.json")
    suspend fun addTask(
        @Body param: TaskCloud
    ): String

    @PUT("me/tasks/{id}.json")
    suspend fun editTask(
        @Path("id") id: String,
        @Body param: TaskCloud
    ): String

    //TODO
    @POST("me/tasks.json")
    suspend fun addTasks(
        @Body param: List<TaskCloud>
    ): List<String>

}