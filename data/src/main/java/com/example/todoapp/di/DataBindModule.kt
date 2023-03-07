package com.example.todoapp.di

import com.example.todoapp.datasource.settings.datastore.SettingsCacheDataSourceImpl
import com.example.todoapp.datasource.tasks.cache.TasksCacheDataSourceImpl
import com.example.todoapp.datasource.tasks.cloud.TasksCloudDataSourceImpl
import com.example.todoapp.exception.HandleDomainError
import com.example.todoapp.exception.HandleError
import com.example.todoapp.repository.SettingsRepository
import com.example.todoapp.repository.TasksRepository
import com.example.todoapp.repository.settings.SettingsCacheDataSource
import com.example.todoapp.repository.settings.SettingsRepositoryImpl
import com.example.todoapp.repository.tasks.TasksCacheDataSource
import com.example.todoapp.repository.tasks.TasksCloudDataSource
import com.example.todoapp.repository.tasks.TasksRepositoryImplNew
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
        tasksRepositoryImpl: TasksRepositoryImplNew
    ): TasksRepository

    @Binds
    fun bindSettingsCacheDataSourceImpl_to_SettingsCacheDataSource(
        settingsCacheDataSourceImpl: SettingsCacheDataSourceImpl
    ): SettingsCacheDataSource

    @Binds
    fun bindSettingsRepositoryImpl_to_SettingsRepository(
        tasksRepositoryImpl: SettingsRepositoryImpl
    ): SettingsRepository

    @Binds
    fun bindHandleDomainError_to_HandleError(
        handleDomainError: HandleDomainError
    ): HandleError<Exception>

}