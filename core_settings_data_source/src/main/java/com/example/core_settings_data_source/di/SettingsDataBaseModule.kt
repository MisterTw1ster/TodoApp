package com.example.core_settings_data_source.di

import android.content.Context
import androidx.room.Room
import com.example.core.di.scope.AppScope
import com.example.core_settings_data_source.SettingsDatabase
import com.example.core_settings_data_source.SettingsDao
import dagger.Module
import dagger.Provides

@Module
class SettingsDataBaseModule  {

    @Provides
    @AppScope
    fun getSettingsDatabase(context: Context): SettingsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SettingsDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @AppScope
    fun provideSettingsDao(db: SettingsDatabase): SettingsDao {
        return db.settingsDao()
    }

    companion object {
        private const val DATABASE_NAME = "settings_database"
    }
}