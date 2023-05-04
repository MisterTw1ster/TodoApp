package com.example.core_task_data_source.cloud

import com.example.core_task_data_source.cloud.models.TaskCloud
import retrofit2.http.*

interface TasksService {

    @GET("{user_id}/tasks.json")
    suspend fun fetchTasks(@Path("user_id") userId: String): Map<String, TaskCloud>

    @PATCH("{user_id}/tasks.json")
    suspend fun saveTask(
        @Body param: Map<String, TaskCloud>,
        @Path("user_id") userId: String
    ): TaskCloud

    @PUT("{user_id}/tasks.json")
    suspend fun replaceTasks(@Body param: Map<String, TaskCloud>, @Path("user_id") userId: String)

    @DELETE("{user_id}/tasks/{id}.json")
    suspend fun deleteTaskById(@Path("id") id: String, @Path("user_id") userId: String)

}