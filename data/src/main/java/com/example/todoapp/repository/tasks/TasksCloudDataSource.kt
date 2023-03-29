package com.example.todoapp.repository.tasks

import com.example.todoapp.models.TaskData

interface TasksCloudDataSource {
    suspend fun fetchTasks(userId: String): List<TaskData>
    suspend fun saveTask(task: TaskData): TaskData
    suspend fun deleteTaskById(id: Long, userId: String): Boolean
    suspend fun replaceTasks(tasks: List<TaskData>, userId: String): Boolean
//    suspend fun addUserBranch(userId: String)
}