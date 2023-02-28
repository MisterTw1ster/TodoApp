package com.example.todoapp.di

import com.example.todoapp.datasource.tasks.cache.TasksCacheDataSourceImpl
import com.example.todoapp.datasource.tasks.cloud.TasksCloudDataSourceImpl
import com.example.todoapp.exception.HandleDomainError
import com.example.todoapp.exception.HandleError
import com.example.todoapp.repository.TasksCacheDataSource
import com.example.todoapp.repository.TasksCloudDataSource
import com.example.todoapp.repository.TasksRepository
import com.example.todoapp.repository.TasksRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataBindModule {

    @Binds
    fun bindTasksCloudDataSourceImpl_to_TasksCloudDataSource(
        tasksRetrofitDataSourceImpl: TasksCloudDataSourceImpl
    ): TasksCloudDataSource

    @Binds
    fun bindTasksCacheDataSourceImpl_to_TasksCacheDataSource(
        tasksRoomDataSourceImpl: TasksCacheDataSourceImpl
    ): TasksCacheDataSource

    @Binds
    fun bindTasksRepositoryImpl_to_TasksRepository(
        tasksRepositoryImpl: TasksRepositoryImpl
    ): TasksRepository

    @Binds
    fun bindHandleDomainError_to_HandleError(
        handleDomainError: HandleDomainError
    ): HandleError<Exception>


//    @Binds
//    fun bindSettingsDataStoreDataSourceImpl_to_SettingsLocalDataSource(
//        tasksRoomDataSourceImpl: SettingsDataStoreDataSourceImpl
//    ): SettingsLocalDataSource
//
//    @Binds
//    fun bindSettingsRepositoryImpl_to_SettingsRepository(
//        tasksRepositoryImpl: SettingsRepositoryImpl
//    ): SettingsRepository

}