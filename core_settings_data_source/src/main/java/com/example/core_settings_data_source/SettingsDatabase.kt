package com.example.core_settings_data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core_settings_data_source.models.SearchItemCache

@Database(entities = [SearchItemCache::class], version = 1)
abstract class SettingsDatabase  : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
}