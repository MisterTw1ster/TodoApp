package com.example.todoapp.repository.tasks

import com.example.todoapp.di.DataScope
import com.example.todoapp.exception.HandleDataRequestNew
import com.example.todoapp.mappers.TaskDataToDomainMapper
import com.example.todoapp.mappers.TaskDomainParamsToDataMapper
import com.example.todoapp.models.TaskDomain
import com.example.todoapp.models.TaskDomainParams
import com.example.todoapp.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@DataScope
class TasksRepositoryImplNew @Inject constructor(
    private val cacheDataSource: TasksCacheDataSource,
    private val cloudDataSource: TasksCloudDataSource,
    private val dataToDomainMapper: TaskDataToDomainMapper,
    private val domainParamsToDataMapper: TaskDomainParamsToDataMapper,
    private val handleDataRequestNew: HandleDataRequestNew
) : TasksRepository {

    override suspend fun observeTasks(): Flow<List<TaskDomain>> {

        syncCacheToCloud()

        return cacheDataSource.observeTasks().map { tasks ->
            tasks.map { task ->
                dataToDomainMapper.transform(task)
            }
        }
    }

    override suspend fun getTaskById(id: Long): TaskDomain {
        val taskData = cacheDataSource.getTaskById(id)
        return dataToDomainMapper.transform(taskData)
    }

    override suspend fun addTask(params: TaskDomainParams): TaskDomain {
        val time = System.currentTimeMillis()
        val taskData = domainParamsToDataMapper.transform(
            params = params.copy(id = time), createdAt = time, changedAt = time
        )
        val block = suspend {
            val taskDataFromCloud = cloudDataSource.saveTask(taskData)
            cacheDataSource.addTask(taskDataFromCloud)
            dataToDomainMapper.transform(taskDataFromCloud)
        }
        val blockFailure = suspend {
            val taskDataFromCache = cacheDataSource.addTask(taskData.copy(outOfSyncNew = true))
        }
        return handleDataRequestNew.handle(block, blockFailure)
    }

    override suspend fun editTask(params: TaskDomainParams): TaskDomain {
        val currentTaskDataFromCache = cacheDataSource.getTaskById(params.id)

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
            val taskDataFromCloud = cloudDataSource.saveTask(taskData)
            cacheDataSource.editTask(taskDataFromCloud)
            dataToDomainMapper.transform(taskDataFromCloud)
        }
        val blockFailure = suspend {
            val taskDataFromCache = cacheDataSource.editTask(taskData.copy(outOfSyncEdit = true))
        }
        return handleDataRequestNew.handle(block, blockFailure)
    }


    override suspend fun deleteTaskById(id: Long): Boolean {
        return try {
            if (cloudDataSource.deleteTask(id)) cacheDataSource.deleteTask(id)
            true
        } catch (e: Exception) {
            cacheDataSource.markOutOfSyncDeleteTask(id)
            false
        }
    }

    override suspend fun syncCacheToCloud() {
        try {
            val tasksDataFromCloud = cloudDataSource.fetchTasks().toMutableList()

            val markDeleteTasksData = cacheDataSource.fetchOutOfSyncMarkDeleteTasks()
            markDeleteTasksData.forEach { taskDataCache ->
                tasksDataFromCloud.find { it.id == taskDataCache.id }
                    ?.takeIf { it.changedAt <= taskDataCache.changedAt }
                    ?.let { tasksDataFromCloud.remove(it) }
            }

            val markEditTasksData = cacheDataSource.fetchOutOfSyncEditTasks()
            markEditTasksData.forEach { task ->
                tasksDataFromCloud.find { it.id == task.id }
                    ?.takeIf { it.changedAt < task.changedAt }
                    ?.let { tasksDataFromCloud[tasksDataFromCloud.indexOf(it)] = task }
            }

            val markNewTasksData = cacheDataSource.fetchOutOfSyncNewTasks()
            tasksDataFromCloud.addAll(markNewTasksData)

            if (cloudDataSource.replaceTasks(tasksDataFromCloud)) {
                cacheDataSource.replaceTasks(tasksDataFromCloud)
            }

        } catch (e: Exception) {
            val d = 3
            val dd = d
        }
    }

}