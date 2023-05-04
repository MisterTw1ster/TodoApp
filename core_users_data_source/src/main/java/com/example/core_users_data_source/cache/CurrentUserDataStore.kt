package com.example.core_users_data_source.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.core.di.scope.AppScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = CurrentUserDataStore.DATA_STORE_NAME
)

@AppScope
class CurrentUserDataStore @Inject constructor(context: Context) {

    private val dataStore = context.dataStore

    fun observeUserId(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USER_ID] ?: UNKNOWN_USER
        }
    }

    suspend fun getUserId(): String {
        return dataStore.data.map { preferences ->
            preferences[USER_ID]
        }.first() ?: UNKNOWN_USER
    }

    suspend fun setUserId(id: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = id
        }
    }

    suspend fun clearUserId() {
        dataStore.edit { preferences ->
            preferences[USER_ID] = UNKNOWN_USER
        }
    }

    companion object {
        const val UNKNOWN_USER = "unknown"
        private val USER_ID = stringPreferencesKey("user_id")
        const val DATA_STORE_NAME = "auth"
    }
}