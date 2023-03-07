package com.example.todoapp.datasource.tasks.cloud

import retrofit2.http.*

interface TasksService {

    @GET("me/tasks.json")
    suspend fun fetchTasks(): Map<String, TaskCloud>

    @PATCH("me/tasks.json")
    suspend fun addTask(@Body param: Map<String, TaskCloud>): TaskCloud

    @PATCH("me/tasks.json")
    suspend fun saveTask(@Body param: Map<String, TaskCloud>): TaskCloud

    @PUT("me/tasks/{id}.json")
    suspend fun editTask(@Path("id") id: String, @Body task: TaskCloud): TaskCloud

    @PATCH("me/tasks.json")
    suspend fun saveTasks(@Body param: Map<String, TaskCloud>)

    @PUT("me/tasks.json")
    suspend fun replaceTasks(@Body param: Map<String, TaskCloud>)

    @DELETE("me/tasks/{id}.json")
    suspend fun deleteTask(@Path("id") id: String)

}