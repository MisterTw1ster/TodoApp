package com.example.todoapp.repository

import com.example.todoapp.models.TaskDomain
import com.example.todoapp.models.TaskDomainParams
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    suspend fun observeTasks(): Flow<List<TaskDomain>>
    suspend fun getTaskById(id: Long): TaskDomain
    suspend fun addTask(params: TaskDomainParams): TaskDomain
    suspend fun editTask(params: TaskDomainParams): TaskDomain
    suspend fun deleteTaskById(id: Long): Boolean
    suspend fun syncCacheToCloud()
    suspend fun addUserBranch(userId: String): String
}