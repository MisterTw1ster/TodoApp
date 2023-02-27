package com.example.todoapp.repository

import com.example.todoapp.datasource.TaskData

interface TasksCloudDataSource {
//    suspend fun fetchTasks(): List<TaskData>
    suspend fun addTask(task: TaskData): TaskData
    suspend fun editTask(task: TaskData): TaskData
    suspend fun syncTasks(tasks: List<TaskData>)
}