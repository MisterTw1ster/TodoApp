//package com.example.todoapp.repository.tasks
//
//import com.example.todoapp.di.DataScope
//import com.example.todoapp.exception.HandleDataRequest
//import com.example.todoapp.mappers.TaskDataToDomainMapper
//import com.example.todoapp.mappers.TaskDomainParamsToDataMapper
//import com.example.todoapp.models.TaskDomain
//import com.example.todoapp.models.TaskDomainParams
//import com.example.todoapp.repository.TasksRepository
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//import javax.inject.Inject
//
//@DataScope
//class TasksRepositoryImpl @Inject constructor(
//    private val cacheDataSource: TasksCacheDataSource,
//    private val cloudDataSource: TasksCloudDataSource,
//    private val dataToDomainMapper: TaskDataToDomainMapper,
//    private val domainParamsToDataMapper: TaskDomainParamsToDataMapper,
//    private val handleDataRequest: HandleDataRequest
//): TasksRepository {
//
//    override suspend fun observeTasks(): Flow<List<TaskDomain>> {
//        return cacheDataSource.observeTasks().map { tasks ->
//            tasks.map { task ->
//                dataToDomainMapper.transform(task)
//            }
//        }
//    }
//
//    override suspend fun getTaskById(id: Long): TaskDomain {
//        val taskData = cacheDataSource.getTaskById(id)
//        return dataToDomainMapper.transform(taskData)
//    }
//
//    override suspend fun addTask(params: TaskDomainParams): TaskDomain = handleDataRequest.handle {
//        val time = System.currentTimeMillis()
//        val taskData =
//            domainParamsToDataMapper.transform(params = params, createdAt = time, changedAt = time)
//        val taskDataFromCache = cacheDataSource.addTask(taskData)
//        val taskDataFromCloud = cloudDataSource.addTask(taskDataFromCache)
//        cacheDataSource.markOutOfSyncEditTask(taskDataFromCache.id)
//        dataToDomainMapper.transform(taskDataFromCloud)
//    }
//
//    override suspend fun editTask(params: TaskDomainParams): TaskDomain = handleDataRequest.handle {
//        val currentTaskDataFromCache = cacheDataSource.getTaskById(params.id)
//
//        if (currentTaskDataFromCache.text == params.text &&
//            currentTaskDataFromCache.importance == params.importance &&
//            currentTaskDataFromCache.deadline == params.deadline &&
//            currentTaskDataFromCache.isDone == params.isDone
//        ) {
//            return@handle dataToDomainMapper.transform(currentTaskDataFromCache)
//        }
//
//        val time = System.currentTimeMillis()
//        val taskData =
//            domainParamsToDataMapper.transform(
//                params = params,
//                createdAt = currentTaskDataFromCache.createdAt,
//                changedAt = time
//            )
//        val taskDataFromCache = cacheDataSource.editTask(taskData)
//        val taskDataFromCloud = cloudDataSource.editTask(taskDataFromCache)
//        cacheDataSource.markOutOfSyncEditTask(taskDataFromCache.id)
//        dataToDomainMapper.transform(taskDataFromCloud)
//    }
//
//    override suspend fun deleteTaskById(id: Long): Boolean {
//        return try {
//            if (cloudDataSource.deleteTask(id)) cacheDataSource.deleteTask(id)
//            true
//        } catch (e: Exception) {
//            cacheDataSource.markOutOfSyncDeleteTask(id)
//
//            false
//        }
//    }
//
//    override suspend fun syncCacheToCloud() {
//        try {
//            val markDeleteTasksData = cacheDataSource.fetchOutOfSyncMarkDeleteTasks()
//            markDeleteTasksData.forEach { task ->
//                if (cloudDataSource.deleteTask(task.id)) cacheDataSource.deleteTask(task.id)
//            }
//
//            val outOfSyncTasksData = cacheDataSource.fetchOutOfSyncEditTasks()
//            if (outOfSyncTasksData.isNotEmpty() && cloudDataSource.saveTasks(outOfSyncTasksData)) {
//                outOfSyncTasksData.forEach { task -> cacheDataSource.markOutOfSyncEditTask(task.id) }
//            }
//
//        } catch (e: Exception) {
//
//        }
//    }
//
//}