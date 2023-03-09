package com.example.todoapp.repository.tasks

import com.example.todoapp.models.TaskData
import kotlinx.coroutines.flow.Flow

interface TasksCacheDataSource {
    suspend fun observeTasks(): Flow<List<TaskData>>
    suspend fun getTaskById(id: Long): TaskData
    suspend fun addTask(task: TaskData): TaskData
    suspend fun editTask(task: TaskData): TaskData
    suspend fun deleteTaskById(id: Long): Boolean

    suspend fun replaceTasks(tasks: List<TaskData>)

    suspend fun markOutOfSyncDeleteTaskById(id: Long)

    suspend fun fetchOutOfSyncMarkDeleteTasks(): List<TaskData>
    suspend fun fetchOutOfSyncEditTasks(): List<TaskData>
    suspend fun fetchOutOfSyncNewTasks(): List<TaskData>

}