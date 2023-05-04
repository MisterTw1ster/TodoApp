package com.example.core_settings_repository

import com.example.core_settings_repository.models.SearchItemData
import kotlinx.coroutines.flow.Flow

interface SettingsCacheDataSource {
    suspend fun observeHideCompletedFilters(): Flow<Boolean>
    suspend fun getHideCompletesFilters(): Boolean
    suspend fun saveHideCompletedFilters(hide: Boolean)
    suspend fun observeTasksSorting(): Flow<String>
    suspend fun getTasksSorting(): String
    suspend fun saveTasksSorting(sortMode: String)
    suspend fun saveSearchItem(item: SearchItemData)
    suspend fun observeSearchItem(section: String): Flow<List<SearchItemData>>
}