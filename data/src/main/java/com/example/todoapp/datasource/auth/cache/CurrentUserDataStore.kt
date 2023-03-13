package com.example.todoapp.datasource.auth.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.todoapp.di.DataScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = CurrentUserDataStore.DATA_STORE_NAME
)

@DataScope
class CurrentUserDataStore @Inject constructor(context: Context) {

    private val dataStore = context.dataStore

    suspend fun getUserId(): String {
        return dataStore.data.map { preferences ->
            preferences[USER_ID]
        }.first() ?: UNKNOWN_USER
    }

    suspend fun saveUserId(id: String) {
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
        private const val UNKNOWN_USER = "unknown"//"Nvp7LsArP1XmFAzS1nmjrRJxyB62"
        private val USER_ID = stringPreferencesKey("user_id")
        const val DATA_STORE_NAME = "auth"
    }
}