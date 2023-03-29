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

    override suspend fun fetchTasks(userId: String): List<TaskData> {
        return api.fetchTasks(userId).map { task ->
            cloudToDataMapper.transform(task.value, userId)
        }
    }

    override suspend fun saveTask(task: TaskData): TaskData {
        val taskCloud = dataToCloudMapper.transform(task)
        val taskCloudNew = api.saveTask(mapOf(taskCloud.id to taskCloud), task.userId)
        return cloudToDataMapper.transform(taskCloudNew, task.userId)
    }

    override suspend fun deleteTaskById(id: Long, userId: String): Boolean {
        api.deleteTaskById(id.toString(), userId)
        return true
    }

    override suspend fun replaceTasks(tasks: List<TaskData>, userId: String): Boolean {
        val tasksCloud = tasks.associate { task ->
            task.id.toString() to dataToCloudMapper.transform(task)
        }
        api.replaceTasks(tasksCloud, userId)
        return true
    }

//    override suspend fun addUserBranch(userId: String) {
//        api.addUserBranch(userId)
//    }
}