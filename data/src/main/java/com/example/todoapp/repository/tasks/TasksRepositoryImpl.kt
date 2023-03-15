package com.example.todoapp.repository.tasks

import com.example.todoapp.di.DataScope
import com.example.todoapp.exception.HandleDataRequest
import com.example.todoapp.mappers.TaskDataToDomainMapper
import com.example.todoapp.mappers.TaskDomainParamsToDataMapper
import com.example.todoapp.models.TaskDomain
import com.example.todoapp.models.TaskDomainParams
import com.example.todoapp.repository.TasksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@DataScope
class TasksRepositoryImpl @Inject constructor(
    private val taskCacheDataSource: TasksCacheDataSource,
    private val taskCloudDataSource: TasksCloudDataSource,
//    private val authCacheDataSource: AuthCacheDataSource,
    private val dataToDomainMapper: TaskDataToDomainMapper,
    private val domainParamsToDataMapper: TaskDomainParamsToDataMapper,
    private val handleDataRequest: HandleDataRequest
) : TasksRepository {

    private var userId = "unknown"
//    private val scope = CoroutineScope(Dispatchers.IO)

//    init {
//        scope.launch {
//            authCacheDataSource.getCurrentUserId().collect { userId = it }
//        }
//    }

    override suspend fun observeTasks(user_id: String): Flow<List<TaskDomain>> {
        userId = user_id
        syncCacheToCloud()
        return taskCacheDataSource.observeTasks().map { tasks ->
            tasks.filter { it.userId == userId }
                 .map { task -> dataToDomainMapper.transform(task) }
        }
    }

    override suspend fun getTaskById(id: Long): TaskDomain {
        val taskData = taskCacheDataSource.getTaskById(id, userId)
        return dataToDomainMapper.transform(taskData)
    }

    override suspend fun addTask(params: TaskDomainParams): TaskDomain {
        val time = System.currentTimeMillis()
        val taskData = domainParamsToDataMapper.transform(
            params = params.copy(id = time), createdAt = time, changedAt = time
        )
        val block = suspend {
            val taskDataFromCloud = taskCloudDataSource.saveTask(taskData, userId)
            taskCacheDataSource.addTask(taskDataFromCloud)
            dataToDomainMapper.transform(taskDataFromCloud)
        }
        val blockFailure = suspend {
            val taskDataFromCache = taskCacheDataSource.addTask(taskData.copy(outOfSyncNew = true))
        }
        return handleDataRequest.handle(block, blockFailure)
    }

    override suspend fun editTask(params: TaskDomainParams): TaskDomain {
        val currentTaskDataFromCache = taskCacheDataSource.getTaskById(params.id, userId)

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
            val taskDataFromCloud = taskCloudDataSource.saveTask(taskData, userId)
            taskCacheDataSource.editTask(taskDataFromCloud)
            dataToDomainMapper.transform(taskDataFromCloud)
        }
        val blockFailure = suspend {
            val taskDataFromCache = taskCacheDataSource.editTask(taskData.copy(outOfSyncEdit = true))
        }
        return handleDataRequest.handle(block, blockFailure)
    }


    override suspend fun deleteTaskById(id: Long): Boolean {
        return try {
            if (taskCloudDataSource.deleteTaskById(id, userId)) taskCacheDataSource.deleteTaskById(id, userId)
            true
        } catch (e: Exception) {
            taskCacheDataSource.markOutOfSyncDeleteTaskById(id, userId)
            false
        }
    }

    override suspend fun syncCacheToCloud() {
        try {
            val tasksDataFromCloud = taskCloudDataSource.fetchTasks(userId).toMutableList()

            val markDeleteTasksData = taskCacheDataSource.fetchOutOfSyncMarkDeleteTasks()
            markDeleteTasksData.forEach { taskDataCache ->
                tasksDataFromCloud.find { it.id == taskDataCache.id }
                    ?.takeIf { it.changedAt <= taskDataCache.changedAt }
                    ?.let { tasksDataFromCloud.remove(it) }
            }

            val markEditTasksData = taskCacheDataSource.fetchOutOfSyncEditTasks()
            markEditTasksData.forEach { task ->
                tasksDataFromCloud.find { it.id == task.id }
                    ?.takeIf { it.changedAt < task.changedAt }
                    ?.let { tasksDataFromCloud[tasksDataFromCloud.indexOf(it)] = task }
            }

            val markNewTasksData = taskCacheDataSource.fetchOutOfSyncNewTasks()
            tasksDataFromCloud.addAll(markNewTasksData)

            if (taskCloudDataSource.replaceTasks(tasksDataFromCloud, userId)) {
                taskCacheDataSource.replaceTasks(tasksDataFromCloud)
            }

        } catch (e: Exception) {

        }
    }

    override suspend fun addUserBranch(userId: String): String {
        taskCloudDataSource.addUserBranch(userId)
        return userId
    }

}