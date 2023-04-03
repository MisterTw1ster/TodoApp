package com.example.todoapp.datasource.settings.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.todoapp.di.DataScope
import com.example.todoapp.repository.settings.SettingsCacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SettingsCacheDataSourceImpl.DATA_STORE_NAME
)

@DataScope
class SettingsCacheDataSourceImpl @Inject constructor(context: Context) : SettingsCacheDataSource {

    private val dataStore = context.dataStore

    override suspend fun observeHideCompletedFilters(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[HIDE_COMPLETED] ?: DEFAULT_HIDE_COMPLETED_VALUE
        }
    }

    override suspend fun getHideCompletesFilters(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[HIDE_COMPLETED] ?: DEFAULT_HIDE_COMPLETED_VALUE
        }.first()
    }

    override suspend fun saveHideCompletedFilters(hide: Boolean) {
        dataStore.edit { preferences ->
            preferences[HIDE_COMPLETED] = hide
        }
    }

    override suspend fun observeTasksSorting(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TASKS_SORTING] ?: DEFAULT_TASKS_SORTING_VALUE
        }
    }

    override suspend fun getTasksSorting(): String {
        return dataStore.data.map { preferences ->
            preferences[TASKS_SORTING] ?: DEFAULT_TASKS_SORTING_VALUE
        }.first()
    }

    override suspend fun saveTasksSorting(sortMode: String) {
        dataStore.edit { preferences ->
            preferences[TASKS_SORTING] = sortMode
        }
    }

    companion object {
        private val HIDE_COMPLETED = booleanPreferencesKey("hide_completed")
        private const val DEFAULT_HIDE_COMPLETED_VALUE = false
        private val TASKS_SORTING = stringPreferencesKey("tasks_sorting")
        private const val DEFAULT_TASKS_SORTING_VALUE = "created_at_desc"

        const val DATA_STORE_NAME = "settings"
    }
}

