package com.example.core_task_repository

import com.example.core.di.scope.AppScope
import com.example.core_domain.models.TaskDomain
import com.example.core_domain.models.TaskDomainParams
import com.example.core_domain.repository.TasksRepository
import com.example.core_task_repository.exception.TasksHandleDataRequest
import com.example.core_task_repository.mappers.TaskDataToDomainMapper
import com.example.core_task_repository.mappers.TaskDomainParamsToDataMapper
import com.example.core_task_repository.models.TaskData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AppScope
class TasksRepositoryImpl @Inject constructor(
    private val taskCacheDataSource: TasksCacheDataSource,
    private val taskCloudDataSource: TasksCloudDataSource,
    private val dataToDomainMapper: TaskDataToDomainMapper,
    private val domainParamsToDataMapper: TaskDomainParamsToDataMapper,
    private val tasksHandleDataRequest: TasksHandleDataRequest
) : TasksRepository {

    override suspend fun observeTasks(userId: String): Flow<List<TaskDomain>> {
        syncCacheToCloud(userId)
        return taskCacheDataSource.observeTasks().map { tasks ->
            tasks.filter { it.userId == userId }
                 .map { task -> dataToDomainMapper.transform(task) }
        }
    }

    override suspend fun getTaskById(id: Long): TaskDomain {
        val taskData = taskCacheDataSource.getTaskById(id)
        return dataToDomainMapper.transform(taskData)
    }

    override suspend fun addTask(params: TaskDomainParams): TaskDomain {
        val time = System.currentTimeMillis()
        val taskData = domainParamsToDataMapper.transform(
            params = params.copy(id = time), createdAt = time, changedAt = time
        )
        val block = suspend {
            val taskDataFromCloud = taskCloudDataSource.saveTask(taskData)
            taskCacheDataSource.addTask(taskDataFromCloud)
            dataToDomainMapper.transform(taskDataFromCloud)
        }
        val blockFailure = suspend {
            val taskDataFromCache = taskCacheDataSource.addTask(taskData.copy(outOfSyncNew = true))
        }
        return tasksHandleDataRequest.handle(block, blockFailure)
    }

    override suspend fun editTask(params: TaskDomainParams): TaskDomain {
        val currentTaskDataFromCache = taskCacheDataSource.getTaskById(params.id)

        if (currentTaskDataFromCache.text == params.text &&
            currentTaskDataFromCache.importance == params.importance &&
            currentTaskDataFromCache.deadline == params.deadline &&
            currentTaskDataFromCache.isDone == params.isDone
        ) {
            return dataToDomainMapper.transform(currentTaskDataFromCache)
        }

        val time = System.currentTimeMillis()
        val taskData =
            domainParamsToDataMapper.transform(
                params = params,
                createdAt = currentTaskDataFromCache.createdAt,
                changedAt = time,
                outOfSyncNew = currentTaskDataFromCache.outOfSyncNew,
                outOfSyncEdit = currentTaskDataFromCache.outOfSyncEdit,
                outOfSyncDelete = currentTaskDataFromCache.outOfSyncDelete
            )
        val block = suspend {
            val taskDataFromCloud = taskCloudDataSource.saveTask(taskData)
            taskCacheDataSource.editTask(taskDataFromCloud)
            dataToDomainMapper.transform(taskDataFromCloud)
        }
        val blockFailure = suspend {
            val taskDataFromCache = taskCacheDataSource.editTask(taskData.copy(outOfSyncEdit = true))
        }
        return tasksHandleDataRequest.handle(block, blockFailure)
    }


    override suspend fun deleteTask(taskDomainParams: TaskDomainParams): Boolean {
        val id = taskDomainParams.id
        val userId = taskDomainParams.userId
        return try {
            if (taskCloudDataSource.deleteTaskById(id, userId)) taskCacheDataSource.deleteTaskById(id)
            true
        } catch (e: Exception) {
            taskCacheDataSource.markOutOfSyncDeleteTaskById(id)
            false
        }
    }

    override suspend fun markForDeletionTask(taskDomainParams: TaskDomainParams): Boolean {
        val id = taskDomainParams.id
        taskCacheDataSource.markOutOfSyncDeleteTaskById(id)
        return true
    }

    override suspend fun uncheckToDeleteTask(taskDomainParams: TaskDomainParams): Boolean {
        val id = taskDomainParams.id
        taskCacheDataSource.uncheckToDeleteTaskById(id)
        return true
    }

    override suspend fun syncCacheToCloud(currentUserId: String?) {
        val usersId = taskCacheDataSource.fetchUsersIdOutOfSyncTasks().toMutableList()
        if (currentUserId != null && !usersId.contains(currentUserId)) {
            usersId.add(0, currentUserId)
        }

        for (id in usersId) {
            val tasksDataFromCloud = mutableListOf<TaskData>()
            try {
                tasksDataFromCloud.addAll(taskCloudDataSource.fetchTasks(id))
            } catch (_: Exception) { }

            val markDeleteTasksData = taskCacheDataSource.fetchOutOfSyncMarkDeleteTasks(id)
            markDeleteTasksData.forEach { taskDataCache ->
                tasksDataFromCloud.find { it.id == taskDataCache.id }
                    ?.takeIf { it.changedAt <= taskDataCache.changedAt }
                    ?.let { tasksDataFromCloud.remove(it) }
            }

            val markEditTasksData = taskCacheDataSource.fetchOutOfSyncEditTasks(id)
            markEditTasksData.forEach { task ->
                tasksDataFromCloud.find { it.id == task.id }
                    ?.takeIf { it.changedAt < task.changedAt }
                    ?.let { tasksDataFromCloud[tasksDataFromCloud.indexOf(it)] = task }
            }

            val markNewTasksData = taskCacheDataSource.fetchOutOfSyncNewTasks(id)
            tasksDataFromCloud.addAll(markNewTasksData)

            try {
                if (taskCloudDataSource.replaceTasks(tasksDataFromCloud, id)) {
                    taskCacheDataSource.replaceTasks(tasksDataFromCloud, id)
                }
            } catch (e: Exception) {
            }
        }

    }

}