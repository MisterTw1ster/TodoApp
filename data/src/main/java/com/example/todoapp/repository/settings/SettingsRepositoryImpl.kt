package com.example.todoapp.repository.settings

import com.example.todoapp.di.DataScope
import com.example.todoapp.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@DataScope
class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataSource: SettingsCacheDataSource,
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

}