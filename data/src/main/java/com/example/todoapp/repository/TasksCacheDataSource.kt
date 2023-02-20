package com.example.todoapp.repository

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.TaskDataParams
import kotlinx.coroutines.flow.Flow

interface TasksCacheDataSource {
    suspend fun observeTasks(): Flow<List<TaskData>>
    suspend fun getTaskById(id: Long): TaskData
    suspend fun addTask(params: TaskDataParams): Long
    suspend fun editTask(id: Long, params: TaskDataParams): Boolean
}