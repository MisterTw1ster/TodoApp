package com.example.todoapp.repository.settings

import kotlinx.coroutines.flow.Flow

interface SettingsCacheDataSource {
    suspend fun observeHideCompletedFilters(): Flow<Boolean>
    suspend fun getHideCompletesFilters(): Boolean
    suspend fun saveHideCompletedFilters(hide: Boolean)
    suspend fun observeTasksSorting(): Flow<String>
    suspend fun getTasksSorting(): String
    suspend fun saveTasksSorting(sortMode: String)
}