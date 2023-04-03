package com.example.todoapp.repository.settings

import kotlinx.coroutines.flow.Flow

interface SettingsCacheDataSource {
    suspend fun observeHideCompletedFilters(): Flow<Boolean>
    suspend fun getHideCompletesFilters(): Boolean
    suspend fun saveHideCompletedFilters(hide: Boolean)
}