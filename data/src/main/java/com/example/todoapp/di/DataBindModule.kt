package com.example.todoapp.di

import com.example.todoapp.datasource.auth.cache.AuthCacheDataSourceImpl
import com.example.todoapp.datasource.auth.cloud.AuthCloudDataSourceImpl
import com.example.todoapp.datasource.settings.cache.SettingsCacheDataSourceImpl
import com.example.todoapp.datasource.tasks.cache.TasksCacheDataSourceImpl
import com.example.todoapp.datasource.tasks.cloud.TasksCloudDataSourceImpl
import com.example.todoapp.exception.HandleDomainError
import com.example.todoapp.exception.HandleError
import com.example.todoapp.repository.AuthRepository
import com.example.todoapp.repository.SettingsRepository
import com.example.todoapp.repository.TasksRepository
import com.example.todoapp.repository.auth.AuthCacheDataSource
import com.example.todoapp.repository.auth.AuthCloudDataSource
import com.example.todoapp.repository.auth.AuthRepositoryImpl
import com.example.todoapp.repository.settings.SettingsCacheDataSource
import com.example.todoapp.repository.settings.SettingsRepositoryImpl
import com.example.todoapp.repository.tasks.TasksCacheDataSource
import com.example.todoapp.repository.tasks.TasksCloudDataSource
import com.example.todoapp.repository.tasks.TasksRepositoryImpl
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
    fun bindSettingsCacheDataSourceImpl_to_SettingsCacheDataSource(
        settingsCacheDataSourceImpl: SettingsCacheDataSourceImpl
    ): SettingsCacheDataSource

    @Binds
    fun bindSettingsRepositoryImpl_to_SettingsRepository(
        tasksRepositoryImpl: SettingsRepositoryImpl
    ): SettingsRepository

    @Binds
    fun bindAuthCacheDataSourceImpl_to_AuthCacheDataSource(
        authCacheDataSourceImpl: AuthCacheDataSourceImpl
    ): AuthCacheDataSource

    @Binds
    fun bindAuthCloudDataSourceImpl_to_AuthCloudDataSource(
        authCloudDataSourceImpl: AuthCloudDataSourceImpl
    ): AuthCloudDataSource

    @Binds
    fun bindAuthRepositoryImpl_to_AuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindHandleDomainError_to_HandleError(
        handleDomainError: HandleDomainError
    ): HandleError<Exception>

}