package com.example.todoapp.datasource.tasks.cloud

import retrofit2.http.*

interface TasksService {

    @GET("me.json")
    suspend fun fetchTasks(): List<TaskCloud>

    @PATCH("me/tasks.json")
    suspend fun addTask(@Body param: Map<String, TaskCloud>): TaskCloud

    @PUT("me/tasks/{id}.json")
    suspend fun editTask(@Path("id") id: String, @Body task: TaskCloud): TaskCloud

    @PATCH("me/tasks.json")
    suspend fun editTasks(@Body param: Map<String, TaskCloud>)

    @DELETE("me/tasks/{id}.json")
    suspend fun deleteTask(@Path("id") id: String)

}