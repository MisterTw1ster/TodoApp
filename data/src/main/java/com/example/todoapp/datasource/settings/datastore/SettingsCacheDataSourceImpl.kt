package com.example.todoapp.datasource.settings.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.todoapp.di.DataScope
import com.example.todoapp.repository.settings.SettingsCacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SettingsCacheDataSourceImpl.DATA_STORE_NAME
)

@DataScope
class SettingsCacheDataSourceImpl @Inject constructor(context: Context) : SettingsCacheDataSource {

    private val dataStore = context.dataStore

    override suspend fun observeSettingHideCompleted(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[HIDE_COMPLETED] ?: false
        }
    }

    override suspend fun saveSettingHideCompleted(hide: Boolean) {
        dataStore.edit { preferences ->
            preferences[HIDE_COMPLETED] = hide
        }
    }

    companion object {
        private val HIDE_COMPLETED = booleanPreferencesKey("hide_completed")
        const val DATA_STORE_NAME = "settings"
    }
}

