package com.example.domain.repository

import com.example.domain.models.SearchItemDomain
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun observeHideCompletedFilters(): Flow<Boolean>
    suspend fun getHideCompletedFilters(): Boolean
    suspend fun saveHideCompletedFilters(hide: Boolean)
    suspend fun observeSortingTasks(): Flow<String>
    suspend fun getSortingTasks(): String
    suspend fun saveSortingTasks(sortMode: String)
    suspend fun saveSearchItem(item: SearchItemDomain)
    suspend fun observeSearchItem(section: String): Flow<List<SearchItemDomain>>
}