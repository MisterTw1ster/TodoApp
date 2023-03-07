package com.example.todoapp.repository.tasks

import com.example.todoapp.models.TaskData

interface TasksCloudDataSource {
    suspend fun fetchTasks(): List<TaskData>
    suspend fun saveTask(task: TaskData): TaskData
    suspend fun addTask(task: TaskData): TaskData
    suspend fun editTask(task: TaskData): TaskData
    suspend fun deleteTask(id: Long): Boolean
    suspend fun saveTasks(tasks: List<TaskData>): Boolean
    suspend fun replaceTasks(tasks: List<TaskData>): Boolean
}