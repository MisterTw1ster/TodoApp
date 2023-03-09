package com.example.todoapp.repository.tasks

import com.example.todoapp.models.TaskData

interface TasksCloudDataSource {
    suspend fun fetchTasks(): List<TaskData>
    suspend fun saveTask(task: TaskData): TaskData
    suspend fun deleteTaskById(id: Long): Boolean
    suspend fun replaceTasks(tasks: List<TaskData>): Boolean
}