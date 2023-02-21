package com.example.todoapp.datasource.tasks.cloud

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.tasks.cloud.mappers.TaskCloudToDataMapper
import com.example.todoapp.datasource.tasks.cloud.mappers.TaskDataToCloudMapper
import com.example.todoapp.repository.TasksCloudDataSource

class TasksCloudDataSourceImpl(
    private val service: TasksService,
    private val cloudToDataMapper: TaskCloudToDataMapper,
    private val dataToCloudMapper: TaskDataToCloudMapper
): TasksCloudDataSource {
    override suspend fun fetchTasks(): List<TaskData> {
        return service.fetchTasks().map { task ->
            cloudToDataMapper.transform(task)
        }
    }

    override suspend fun addTask(task: TaskData): String {
        val taskCloud = dataToCloudMapper.transform(task)
        return service.addTask(taskCloud)
    }

    override suspend fun editTask(id: Long, task: TaskData): String {
        val taskCloud = dataToCloudMapper.transform(task)
        return service.editTask(taskCloud.id, taskCloud)
    }

    override suspend fun addTasks(tasks: List<TaskData>): List<String> {
        val tasksCloud = tasks.map { task ->
            dataToCloudMapper.transform(task)
        }
        return service.addTasks(tasksCloud)
    }
}