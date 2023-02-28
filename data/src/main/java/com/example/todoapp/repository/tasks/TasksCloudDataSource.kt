package com.example.todoapp.repository.tasks

import com.example.todoapp.models.TaskData

interface TasksCloudDataSource {
    suspend fun fetchTasks(): List<TaskData>
    suspend fun addTask(task: TaskData): TaskData
    suspend fun editTask(task: TaskData): TaskData

    suspend fun editTasks(tasks: List<TaskData>): Boolean
}