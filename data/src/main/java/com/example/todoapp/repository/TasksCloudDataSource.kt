package com.example.todoapp.repository

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.tasks.cloud.TaskCloud

interface TasksCloudDataSource {
    suspend fun fetchTasks(): List<TaskData>
    suspend fun addTask(task: TaskData): String
    suspend fun editTask(id: Long, task: TaskData): String
    suspend fun addTasks(tasks: List<TaskData>): List<String>
}