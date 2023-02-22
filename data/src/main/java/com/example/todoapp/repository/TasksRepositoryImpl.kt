package com.example.todoapp.repository

import com.example.todoapp.exception.HandleDataRequest
import com.example.todoapp.mappers.TaskDataToDomainMapper
import com.example.todoapp.mappers.TaskDomainParamsToDataMapper
import com.example.todoapp.models.TaskDomain
import com.example.todoapp.models.TaskDomainParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasksRepositoryImpl(
    private val cacheDataSource: TasksCacheDataSource,
    private val cloudDataSource: TasksCloudDataSource,
    private val dataToDomainMapper: TaskDataToDomainMapper,
    private val domainParamsToDataMapper: TaskDomainParamsToDataMapper,
    private val handleDataRequest: HandleDataRequest
): TasksRepository {
    override suspend fun observeTasks(): Flow<List<TaskDomain>> {
        return cacheDataSource.observeTasks().map { tasks ->
            tasks.map { task ->
                dataToDomainMapper.transform(task)
            }
        }
    }

    override suspend fun getTaskById(id: Long): TaskDomain = handleDataRequest.handle {
        val taskData = cacheDataSource.getTaskById(id)
        dataToDomainMapper.transform(taskData)
    }

    override suspend fun addTask(params: TaskDomainParams): TaskDomain = handleDataRequest.handle {
        val time = System.currentTimeMillis()
        val taskData =
            domainParamsToDataMapper.transform(params = params, createdAt = time, changedAt = time)
        val taskDataCache = cacheDataSource.addTask(taskData)
        val taskDataCloud = cloudDataSource.addTask(taskDataCache)
        dataToDomainMapper.transform(taskDataCloud)
    }

    override suspend fun editTask(params: TaskDomainParams): TaskDomain = handleDataRequest.handle {
        val time = System.currentTimeMillis()
        val taskData =
            domainParamsToDataMapper.transform(params = params, createdAt = time, changedAt = time)
        val taskDataCache = cacheDataSource.editTask(taskData)
        val taskDataCloud = cloudDataSource.editTask(taskDataCache)
        dataToDomainMapper.transform(taskDataCloud)
    }
}