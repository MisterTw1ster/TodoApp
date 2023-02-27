package com.example.todoapp.datasource.tasks.cloud

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.tasks.cloud.mappers.TaskCloudToDataMapper
import com.example.todoapp.datasource.tasks.cloud.mappers.TaskDataToCloudMapper
import com.example.todoapp.repository.TasksCloudDataSource

class TasksCloudDataSourceImpl(
    private val api: TasksService,
    private val cloudToDataMapper: TaskCloudToDataMapper,
    private val dataToCloudMapper: TaskDataToCloudMapper
): TasksCloudDataSource {
    override suspend fun fetchTasks(): List<TaskData> {
        return api.fetchTasks().map { task ->
            cloudToDataMapper.transform(task)
        }
    }

    override suspend fun addTask(task: TaskData): TaskData {
        val taskCloud = dataToCloudMapper.transform(task)
        val taskCloudNew = api.addTask(taskCloud)
        return cloudToDataMapper.transform(taskCloudNew)
    }

    override suspend fun editTask(task: TaskData): TaskData {
        val taskCloud = dataToCloudMapper.transform(task)
        val taskCloudNew = api.editTask(taskCloud.id, taskCloud)
        return cloudToDataMapper.transform(taskCloudNew)
    }

    override suspend fun editTasks(tasks: List<TaskData>): Boolean {
        val tasksCloud = tasks.associate { task ->
            task.id.toString() to dataToCloudMapper.transform(task)
        }
        api.editTasks(tasksCloud)
        return true
//        return tasksCloudNew.map { task -> cloudToDataMapper.transform(task) }
    }
}