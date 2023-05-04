package com.example.core_task_data_source.cache

import com.example.core.di.scope.AppScope
import com.example.core_task_repository.TasksCacheDataSource
import com.example.core_task_repository.models.TaskData
import com.example.core_task_data_source.cache.mappers.TaskCacheToDataMapper
import com.example.core_task_data_source.cache.mappers.TaskDataToCacheMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AppScope
class TasksCacheDataSourceImpl @Inject constructor(
    private val dao: TasksDao,
    private val cacheToDataMapper: TaskCacheToDataMapper,
    private val dataToCacheMapper: TaskDataToCacheMapper
) : TasksCacheDataSource {

    override suspend fun observeTasks(): Flow<List<TaskData>> {
        return dao.observeTasks().map { tasksCache ->
            tasksCache.map { taskCache -> cacheToDataMapper.transform(taskCache) }
        }
    }

    override suspend fun getTaskById(id: Long): TaskData {
        val taskCache = dao.getTaskById(id)
        return cacheToDataMapper.transform(taskCache)
    }

    override suspend fun addTask(task: TaskData): TaskData {
        val taskCache = dataToCacheMapper.transform(task)
        dao.addTask(taskCache)
        return task
    }

    override suspend fun editTask(task: TaskData): TaskData {
        val taskCache = dataToCacheMapper.transform(task)
        dao.editTask(taskCache)
        return task
    }

    override suspend fun deleteTaskById(id: Long): Boolean {
        dao.deleteTaskById(id)
        return true
    }

    override suspend fun replaceTasks(tasks: List<TaskData>, userId: String) {
        val tasksCache = tasks.map { task -> dataToCacheMapper.transform(task) }
        dao.replaceAll(tasksCache, userId)
    }

    override suspend fun markOutOfSyncDeleteTaskById(id: Long) {
        val time = System.currentTimeMillis()
        dao.markOutOfSyncDeleteTaskById(id, time)
    }

    override suspend fun uncheckToDeleteTaskById(id: Long) {
        val time = System.currentTimeMillis()
        dao.uncheckToDeleteTaskById(id, time)
    }

    override suspend fun fetchUsersIdOutOfSyncTasks(): List<String> {
        return dao.fetchUsersIdOutOfSyncTasks()
    }


    override suspend fun fetchOutOfSyncNewTasks(userId: String): List<TaskData> {
        return dao.fetchOutOfSyncNewTasks(userId).map { taskCache ->
            cacheToDataMapper.transform(
                taskCache.copy(
                    outOfSyncNew = false,
                    outOfSyncEdit = false,
                    outOfSyncDelete = false
                )
            )
        }
    }

    override suspend fun fetchOutOfSyncEditTasks(userId: String): List<TaskData> {
        return dao.fetchOutOfSyncEditTasks(userId).map { taskCache ->
            cacheToDataMapper.transform(
                taskCache.copy(
                    outOfSyncNew = false,
                    outOfSyncEdit = false,
                    outOfSyncDelete = false
                )
            )
        }
    }

    override suspend fun fetchOutOfSyncMarkDeleteTasks(userId: String): List<TaskData> {
        return dao.fetchOutOfSyncMarkDeleteTasks(userId).map { taskCache ->
            cacheToDataMapper.transform(
                taskCache.copy(
                    outOfSyncNew = false,
                    outOfSyncEdit = false,
                    outOfSyncDelete = false
                )
            )
        }
    }
}