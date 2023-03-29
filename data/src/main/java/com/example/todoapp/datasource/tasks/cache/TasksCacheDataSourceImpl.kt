package com.example.todoapp.datasource.tasks.cache

import com.example.todoapp.models.TaskData
import com.example.todoapp.datasource.tasks.cache.mappers.TaskCacheToDataMapper
import com.example.todoapp.datasource.tasks.cache.mappers.TaskDataToCacheMapper
import com.example.todoapp.di.DataScope
import com.example.todoapp.repository.tasks.TasksCacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@DataScope
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