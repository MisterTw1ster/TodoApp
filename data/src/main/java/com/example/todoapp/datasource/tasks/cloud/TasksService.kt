package com.example.todoapp.datasource.tasks.cloud

import retrofit2.http.*

interface TasksService {

    @GET("me/tasks.json")
    suspend fun fetchTasks(): Map<String, TaskCloud>

    @PATCH("me/tasks.json")
    suspend fun saveTask(@Body param: Map<String, TaskCloud>): TaskCloud

    @PUT("me/tasks.json")
    suspend fun replaceTasks(@Body param: Map<String, TaskCloud>)

    @DELETE("me/tasks/{id}.json")
    suspend fun deleteTaskById(@Path("id") id: String)

}