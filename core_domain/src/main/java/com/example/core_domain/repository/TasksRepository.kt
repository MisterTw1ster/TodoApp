package com.example.core_domain.repository

import com.example.core_domain.models.TaskDomain
import com.example.core_domain.models.TaskDomainParams
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    suspend fun initAndObserveTasks(userId: String): Flow<List<TaskDomain>>
    suspend fun getTaskById(id: Long): TaskDomain
    suspend fun addTask(params: TaskDomainParams): TaskDomain
    suspend fun editTask(params: TaskDomainParams): TaskDomain
    suspend fun deleteTask(taskDomainParams: TaskDomainParams): Boolean
    suspend fun markForDeletionTask(taskDomainParams: TaskDomainParams): Boolean
    suspend fun uncheckToDeleteTask(taskDomainParams: TaskDomainParams): Boolean
    suspend fun syncCacheToCloud(currentUserId: String?)
}