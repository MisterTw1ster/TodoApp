package com.example.todoapp.repository.settings

import kotlinx.coroutines.flow.Flow

interface SettingsCacheDataSource {
    suspend fun observeSettingHideCompleted(): Flow<Boolean>
    suspend fun saveSettingHideCompleted(hide: Boolean)
}