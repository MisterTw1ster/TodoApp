package com.example.todoapp.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun observeHideCompletedFilters(): Flow<Boolean>
    suspend fun getHideCompletedFilters(): Boolean
    suspend fun saveHideCompletedFilters(hide: Boolean)
    suspend fun observeSortingTasks(): Flow<String>
    suspend fun getSortingTasks(): String
    suspend fun saveSortingTasks(sortMode: String)
}