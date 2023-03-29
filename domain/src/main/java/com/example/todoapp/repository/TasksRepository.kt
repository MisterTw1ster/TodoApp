package com.example.todoapp.repository

import com.example.todoapp.models.TaskDomain
import com.example.todoapp.models.TaskDomainParams
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    suspend fun observeTasks(userId: String): Flow<List<TaskDomain>>
    suspend fun getTaskById(id: Long): TaskDomain
    suspend fun addTask(params: TaskDomainParams): TaskDomain
    suspend fun editTask(params: TaskDomainParams): TaskDomain
    suspend fun deleteTaskById(taskDomainParams: TaskDomainParams): Boolean
    suspend fun syncCacheToCloud(currentUserId: String?)
//    suspend fun addUserBranch(userId: String): String
}