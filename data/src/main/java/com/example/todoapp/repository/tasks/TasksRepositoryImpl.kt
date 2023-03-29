package com.example.todoapp.repository.tasks

import com.example.todoapp.di.DataScope
import com.example.todoapp.exception.HandleDataRequest
import com.example.todoapp.mappers.TaskDataToDomainMapper
import com.example.todoapp.mappers.TaskDomainParamsToDataMapper
import com.example.todoapp.models.TaskData
import com.example.todoapp.models.TaskDomain
import com.example.todoapp.models.TaskDomainParams
import com.example.todoapp.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@DataScope
class TasksRepositoryImpl @Inject constructor(
    private val taskCacheDataSource: TasksCacheDataSource,
    private val taskCloudDataSource: TasksCloudDataSource,
    private val dataToDomainMapper: TaskDataToDomainMapper,
    private val domainParamsToDataMapper: TaskDomainParamsToDataMapper,
    private val handleDataRequest: HandleDataRequest
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
        return handleDataRequest.handle(block, blockFailure)
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
        return handleDataRequest.handle(block, blockFailure)
    }


    override suspend fun deleteTaskById(taskDomainParams: TaskDomainParams): Boolean {
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

    override suspend fun syncCacheToCloud(currentUserId: String?) {
        val usersId = taskCacheDataSource.fetchUsersIdOutOfSyncTasks().toMutableList()
        if (currentUserId != null && !usersId.contains(currentUserId)) {
            usersId.add(0, currentUserId)
        }

        for (id in usersId) {
            val tasksDataFromCloud = mutableListOf<TaskData>()
            try {
                tasksDataFromCloud.addAll(taskCloudDataSource.fetchTasks(id))
            } catch (e: Exception) {
            }

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