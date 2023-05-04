package com.example.core_settings_data_source

import com.example.core.di.scope.AppScope
import com.example.core_settings_data_source.mappers.SearchItemCacheToDataMapper
import com.example.core_settings_data_source.mappers.SearchItemDataToCacheMapper
import com.example.core_settings_repository.SettingsCacheDataSource
import com.example.core_settings_repository.models.SearchItemData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AppScope
class SettingsCacheDataSourceImpl @Inject constructor(
    private val dao: SettingsDao,
    private val settingsDataStore: SettingsDataStore,
    private val searchItemCacheToDataMapper: SearchItemCacheToDataMapper,
    private val searchItemDataToCacheMapper: SearchItemDataToCacheMapper
) : SettingsCacheDataSource {

    override suspend fun observeHideCompletedFilters(): Flow<Boolean> {
        return settingsDataStore.observeHideCompletedFilters()
    }

    override suspend fun getHideCompletesFilters(): Boolean {
       return settingsDataStore.getHideCompletesFilters()
    }

    override suspend fun saveHideCompletedFilters(hide: Boolean) {
        settingsDataStore.saveHideCompletedFilters(hide)
    }

    override suspend fun observeTasksSorting(): Flow<String> {
        return settingsDataStore.observeTasksSorting()
    }

    override suspend fun getTasksSorting(): String {
        return settingsDataStore.getTasksSorting()
    }

    override suspend fun saveTasksSorting(sortMode: String) {
        settingsDataStore.saveTasksSorting(sortMode)
    }

    override suspend fun saveSearchItem(item: SearchItemData) {
        val itemCache = searchItemDataToCacheMapper.transform(item)
        dao.saveSearchItem(itemCache)
    }

    override suspend fun observeSearchItem(section: String): Flow<List<SearchItemData>> {
        return  dao.observeSearchItem(section).map { itemList ->
            itemList.map { item -> searchItemCacheToDataMapper.transform(item) }
        }
    }

}

