package com.example.todoapp.datasource.tasks.cache

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.TaskDataParams
import com.example.todoapp.datasource.tasks.cache.mappers.TaskCacheToDataMapper
import com.example.todoapp.datasource.tasks.cache.mappers.TaskDataParamsToCacheMapper
import com.example.todoapp.datasource.tasks.cache.mappers.TaskDataToCacheMapper
import com.example.todoapp.repository.TasksCacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasksCacheDataSourceImpl(
    private val dao: TasksDao,
    private val cacheToDataMapper: TaskCacheToDataMapper,
    private val dataToCacheMapper: TaskDataToCacheMapper
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

    override suspend fun addTask(task: TaskData): TaskData {
//        val taskCache =
//            dataParamsToCacheMapper.transform(
//                params = params,
//                createdAt = params.time,
//                changedAt = params.time
//            )
        val taskCache = dataToCacheMapper.transform(task)
        val taskCacheNew = dao.addTask(taskCache)
        return cacheToDataMapper.transform(taskCacheNew)
    }

    override suspend fun editTask(task: TaskData): TaskData {
        val currentTaskCache = dao.getTaskById(task.id)

        if (currentTaskCache.text == task.text &&
            currentTaskCache.importance == task.importance &&
            currentTaskCache.deadline == task.deadline &&
            currentTaskCache.isDone == task.isDone) {
            return task
        }

//        val taskCache =
//            dataParamsToCacheMapper.transform(
//                params = params,
//                createdAt = currentTaskCache.createdAt,
//                changedAt = params.time
//            )
        val taskCache = dataToCacheMapper.transform(task)
        val taskCacheNew = dao.editTask(taskCache)
        return cacheToDataMapper.transform(taskCacheNew)
    }
}