package com.example.core_task_repository

import com.example.core_task_repository.models.TaskData
import kotlinx.coroutines.flow.Flow

interface TasksCacheDataSource {
    suspend fun observeTasks(): Flow<List<TaskData>>
    suspend fun getTaskById(id: Long): TaskData
    suspend fun addTask(task: TaskData): TaskData
    suspend fun editTask(task: TaskData): TaskData
    suspend fun deleteTaskById(id: Long): Boolean

    suspend fun replaceTasks(tasks: List<TaskData>, userId: String)

    suspend fun markOutOfSyncDeleteTaskById(id: Long)
    suspend fun uncheckToDeleteTaskById(id: Long)

    suspend fun fetchUsersIdOutOfSyncTasks(): List<String>
    suspend fun fetchOutOfSyncMarkDeleteTasks(userId: String): List<TaskData>
    suspend fun fetchOutOfSyncEditTasks(userId: String): List<TaskData>
    suspend fun fetchOutOfSyncNewTasks(userId: String): List<TaskData>

}