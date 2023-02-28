package com.example.todoapp.repository.settings

import com.example.todoapp.di.DataScope
import com.example.todoapp.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@DataScope
class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataSource: SettingsCacheDataSource,
): SettingsRepository {

    override suspend fun observeSettingHideCompleted(): Flow<Boolean> {
        return settingsDataSource.observeSettingHideCompleted()
    }

    override suspend fun saveSettingHideCompleted(hide: Boolean) {
        settingsDataSource.saveSettingHideCompleted(hide)
    }

}