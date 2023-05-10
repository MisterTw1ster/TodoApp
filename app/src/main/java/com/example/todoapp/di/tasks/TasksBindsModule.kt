package com.example.todoapp.di.tasks

import com.example.core.di.qualifier.Tasks
import com.example.core_domain.exception.HandleError
import com.example.core_domain.exception.tasks.HandleDomainToStringError
import com.example.core_domain.repository.TasksRepository
import com.example.core_task_data_source.cache.TasksCacheDataSourceImpl
import com.example.core_task_data_source.cloud.TasksCloudDataSourceImpl
import com.example.core_task_repository.TasksCacheDataSource
import com.example.core_task_repository.TasksCloudDataSource
import com.example.core_task_repository.TasksRepositoryImpl
import com.example.core_task_repository.exception.TasksHandleDataToDomainError
import dagger.Binds
import dagger.Module

@Module
interface TasksBindsModule {

    @Binds
    fun bindTasksCacheDataSourceImpl_to_TasksCacheDataSource(
        tasksCacheDataSourceImpl: TasksCacheDataSourceImpl
    ): TasksCacheDataSource

    @Binds
    fun bindTasksCloudDataSourceImpl_to_TasksCloudDataSource(
        tasksCloudDataSourceImpl: TasksCloudDataSourceImpl
    ): TasksCloudDataSource

    @Binds
    @Tasks
    fun bindTasksHandleDataToDomainError_to_HandleErrorException(
        tasksHandleDataToDomainError: TasksHandleDataToDomainError
    ): HandleError<Exception>

    @Binds
    fun bindHandleDomainToStringError_to_HandleErrorString(
        handleDataToDomainError: HandleDomainToStringError
    ): HandleError<String>

    @Binds
    fun bindTasksRepositoryImpl_to_TasksRepository(
        tasksRepositoryImpl: TasksRepositoryImpl
    ): TasksRepository

}