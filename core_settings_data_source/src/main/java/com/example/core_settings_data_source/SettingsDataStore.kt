package com.example.core_settings_data_source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.core.di.scope.AppScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SettingsDataStore.DATA_STORE_NAME
)

@AppScope
class SettingsDataStore @Inject constructor(context: Context) {

    private val dataStore = context.dataStore

    fun observeHideCompletedFilters(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[HIDE_COMPLETED] ?: DEFAULT_HIDE_COMPLETED_VALUE
        }
    }

    suspend fun getHideCompletesFilters(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[HIDE_COMPLETED] ?: DEFAULT_HIDE_COMPLETED_VALUE
        }.first()
    }

    suspend fun saveHideCompletedFilters(hide: Boolean) {
        dataStore.edit { preferences ->
            preferences[HIDE_COMPLETED] = hide
        }
    }

    fun observeTasksSorting(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TASKS_SORTING] ?: DEFAULT_TASKS_SORTING_VALUE
        }
    }

    suspend fun getTasksSorting(): String {
        return dataStore.data.map { preferences ->
            preferences[TASKS_SORTING] ?: DEFAULT_TASKS_SORTING_VALUE
        }.first()
    }

    suspend fun saveTasksSorting(sortMode: String) {
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