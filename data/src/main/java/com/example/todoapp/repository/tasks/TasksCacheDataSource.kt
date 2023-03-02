package com.example.todoapp.repository.tasks

import com.example.todoapp.models.TaskData
import kotlinx.coroutines.flow.Flow

interface TasksCacheDataSource {
    suspend fun observeTasks(): Flow<List<TaskData>>
    suspend fun getTaskById(id: Long): TaskData
    suspend fun addTask(task: TaskData): TaskData
    suspend fun editTask(task: TaskData): TaskData
    suspend fun deleteTask(id: Long): Boolean

    suspend fun markAsSync(id: Long)
    suspend fun markDeleteAfterSync(id: Long)

    suspend fun fetchOutOfSyncMarkDeleteTasks(): List<TaskData>
    suspend fun fetchOutOfSyncTasks(): List<TaskData>

}