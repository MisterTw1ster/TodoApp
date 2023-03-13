package com.example.todoapp.repository.tasks

import com.example.todoapp.models.TaskData
import kotlinx.coroutines.flow.Flow

interface TasksCacheDataSource {
    suspend fun observeTasks(): Flow<List<TaskData>>
    suspend fun getTaskById(id: Long, userId: String): TaskData
    suspend fun addTask(task: TaskData): TaskData
    suspend fun editTask(task: TaskData): TaskData
    suspend fun deleteTaskById(id: Long, userId: String): Boolean

    suspend fun replaceTasks(tasks: List<TaskData>)

    suspend fun markOutOfSyncDeleteTaskById(id: Long, userId: String)

    suspend fun fetchOutOfSyncMarkDeleteTasks(): List<TaskData>
    suspend fun fetchOutOfSyncEditTasks(): List<TaskData>
    suspend fun fetchOutOfSyncNewTasks(): List<TaskData>

}