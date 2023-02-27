package com.example.todoapp.datasource.tasks.cache

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.tasks.cache.mappers.TaskCacheToDataMapper
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
        val taskCache = dataToCacheMapper.transform(task)
        val id = dao.addTask(taskCache)
        return task.copy(id = id)
    }

    override suspend fun editTask(task: TaskData): TaskData {
        val taskCache = dataToCacheMapper.transform(task)
        dao.editTask(taskCache)
        return task
    }

    override suspend fun markAsSync(id: Long) {
        dao.markIsSyncTask(id)
    }

    override suspend fun fetchOutOfSyncNewTasks(): List<TaskData> {
        return dao.fetchOutOfSyncNewTasks().map { taskCache ->
            cacheToDataMapper.transform(taskCache)
        }
    }

    override suspend fun fetchOutOfSyncEditTasks(): List<TaskData> {
        return dao.fetchOutOfSyncEditTasks().map { taskCache ->
            cacheToDataMapper.transform(taskCache)
        }
    }
}