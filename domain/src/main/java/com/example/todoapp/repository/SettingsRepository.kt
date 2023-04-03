package com.example.todoapp.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun observeHideCompletedFilters(): Flow<Boolean>
    suspend fun getHideCompletedFilters(): Boolean
    suspend fun saveHideCompletedFilters(hide: Boolean)
}