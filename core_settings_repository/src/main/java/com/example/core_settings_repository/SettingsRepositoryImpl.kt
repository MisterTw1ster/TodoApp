package com.example.core_settings_repository

import com.example.core.di.scope.AppScope
import com.example.domain.models.SearchItemDomain
import com.example.domain.repository.SettingsRepository
import com.example.core_settings_repository.mappers.SearchItemDataToDomainMapper
import com.example.core_settings_repository.mappers.SearchItemDomainToDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AppScope
class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataSource: SettingsCacheDataSource,
    private val searchItemDataToDomainMapper: SearchItemDataToDomainMapper,
    private val searchItemDomainToDataMapper: SearchItemDomainToDataMapper
): SettingsRepository {

    override suspend fun observeHideCompletedFilters(): Flow<Boolean> {
        return settingsDataSource.observeHideCompletedFilters()
    }

    override suspend fun getHideCompletedFilters(): Boolean {
        return settingsDataSource.getHideCompletesFilters()
    }

    override suspend fun saveHideCompletedFilters(hide: Boolean) {
        settingsDataSource.saveHideCompletedFilters(hide)
    }

    override suspend fun observeSortingTasks(): Flow<String> {
        return settingsDataSource.observeTasksSorting()
    }

    override suspend fun getSortingTasks(): String {
        return settingsDataSource.getTasksSorting()
    }

    override suspend fun saveSortingTasks(sortMode: String) {
        settingsDataSource.saveTasksSorting(sortMode)
    }

    override suspend fun saveSearchItem(item: SearchItemDomain) {
        val itemData = searchItemDomainToDataMapper.transform(item)
        settingsDataSource.saveSearchItem(itemData)
    }

    override suspend fun observeSearchItem(section: String): Flow<List<SearchItemDomain>> {
        return settingsDataSource.observeSearchItem(section).map { list ->
            list.map { item -> searchItemDataToDomainMapper.transform(item)}
        }
    }

}