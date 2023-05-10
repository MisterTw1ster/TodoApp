package com.example.todoapp.di.settings

import com.example.core_domain.repository.SettingsRepository
import com.example.core_settings_data_source.SettingsCacheDataSourceImpl
import com.example.core_settings_repository.SettingsCacheDataSource
import com.example.core_settings_repository.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface SettingsBindsModule {

    @Binds
    fun bindSettingsCacheDataSourceImpl_to_SettingsCacheDataSource(
        settingsCacheDataSource: SettingsCacheDataSourceImpl
    ): SettingsCacheDataSource

    @Binds
    fun bindSettingsRepositoryImpl_to_SettingsRepository(
        settingsRepository: SettingsRepositoryImpl
    ): SettingsRepository

}