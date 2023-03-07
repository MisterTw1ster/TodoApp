package com.example.todoapp.datasource.tasks.cloud

import com.example.todoapp.datasource.tasks.cloud.mappers.TaskCloudToDataMapper
import com.example.todoapp.datasource.tasks.cloud.mappers.TaskDataToCloudMapper
import com.example.todoapp.di.DataScope
import com.example.todoapp.models.TaskData
import com.example.todoapp.repository.tasks.TasksCloudDataSource
import javax.inject.Inject

@DataScope
class TasksCloudDataSourceImpl @Inject constructor(
    private val api: TasksService,
    private val cloudToDataMapper: TaskCloudToDataMapper,
    private val dataToCloudMapper: TaskDataToCloudMapper
): TasksCloudDataSource {

    override suspend fun fetchTasks(): List<TaskData> {
        return api.fetchTasks().map { task ->
            cloudToDataMapper.transform(task.value)
        }
    }

    override suspend fun saveTask(task: TaskData): TaskData {
        val taskCloud = dataToCloudMapper.transform(task)
        val taskCloudNew = api.saveTask(mapOf(taskCloud.id to taskCloud))
        return cloudToDataMapper.transform(taskCloudNew)
    }

    override suspend fun addTask(task: TaskData): TaskData {
        val taskCloud = dataToCloudMapper.transform(task)
        val taskCloudNew = api.addTask(mapOf(taskCloud.id to taskCloud))
        return cloudToDataMapper.transform(taskCloudNew)
    }

    override suspend fun editTask(task: TaskData): TaskData {
        val taskCloud = dataToCloudMapper.transform(task)
        val taskCloudNew = api.editTask(taskCloud.id, taskCloud)
        return cloudToDataMapper.transform(taskCloudNew)
    }

    override suspend fun deleteTask(id: Long): Boolean {
        api.deleteTask(id.toString())
        return true
    }

    override suspend fun saveTasks(tasks: List<TaskData>): Boolean {
        val tasksCloud = tasks.associate { task ->
            task.id.toString() to dataToCloudMapper.transform(task)
        }
        api.saveTasks(tasksCloud)
        return true
    }

    override suspend fun replaceTasks(tasks: List<TaskData>): Boolean {
        val tasksCloud = tasks.associate { task ->
            task.id.toString() to dataToCloudMapper.transform(task)
        }
        api.replaceTasks(tasksCloud)
        return true
    }
}