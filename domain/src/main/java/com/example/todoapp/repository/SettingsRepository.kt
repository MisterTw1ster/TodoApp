package com.example.todoapp.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun observeSettingHideCompleted(): Flow<Boolean>
    suspend fun saveSettingHideCompleted(hide: Boolean)
}