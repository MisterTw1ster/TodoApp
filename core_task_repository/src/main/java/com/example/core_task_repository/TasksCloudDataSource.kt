package com.example.core_task_repository

import com.example.core_task_repository.models.TaskData

interface TasksCloudDataSource {
    suspend fun fetchTasks(userId: String): List<TaskData>
    suspend fun saveTask(task: TaskData): TaskData
    suspend fun deleteTaskById(id: Long, userId: String): Boolean
    suspend fun replaceTasks(tasks: List<TaskData>, userId: String): Boolean
}