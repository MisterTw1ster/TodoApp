package com.example.todoapp.datasource.tasks.cache

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.TaskDataParams
import com.example.todoapp.datasource.tasks.cache.mappers.TaskCacheToDataMapper
import com.example.todoapp.datasource.tasks.cache.mappers.TaskDataParamsToCacheMapper
import com.example.todoapp.repository.TasksCacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasksCacheDataSourceImpl(
    private val dao: TasksDao,
    private val cacheToDataMapper: TaskCacheToDataMapper,
    private val dataParamsToCacheMapper: TaskDataParamsToCacheMapper
) : TasksCacheDataSource {

    override suspend fun observeTasks(): Flow<List<TaskData>> {
        return dao.observeTasks().map { tasksCache ->
            tasksCache.map { taskCache ->
                cacheToDataMapper.transform(taskCache)
            }
        }
    }

    override suspend fun getTaskById(id: Long): TaskData {
        val taskCache = dao.getTaskById(id)
        return cacheToDataMapper.transform(taskCache)
    }

    override suspend fun addTask(params: TaskDataParams): Long {
        val taskCache =
            dataParamsToCacheMapper.transform(
                params = params,
                createdAt = params.time,
                changedAt = params.time
            )
        return dao.addTask(taskCache)
    }

    override suspend fun editTask(id: Long, params: TaskDataParams): Boolean {
        val currentTaskCache = dao.getTaskById(id)

        if (currentTaskCache.text == params.text &&
            currentTaskCache.importance == params.importance &&
            currentTaskCache.deadline == params.deadline &&
            currentTaskCache.isDone == params.isDone) {
            return false
        }

        val taskCache =
            dataParamsToCacheMapper.transform(
                params = params,
                createdAt = currentTaskCache.createdAt,
                changedAt = params.time
            )
        dao.editTask(taskCache)
        return true
    }
}