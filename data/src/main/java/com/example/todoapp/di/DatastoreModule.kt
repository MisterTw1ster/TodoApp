//package com.example.todoapp.di
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.PreferenceDataStoreFactory
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.preferencesDataStoreFile
//import dagger.Module
//import dagger.Provides
//
//@Module
//class DatastoreModule {
//
//    @Provides
//    @DataScope
//    fun getSettingsDataStore(context: Context): DataStore<Preferences> {
//        return PreferenceDataStoreFactory.create(
//            produceFile = { context.applicationContext.preferencesDataStoreFile(STORE_NAME) }
//        )
//    }
//
//    companion object {
//        private const val STORE_NAME = "settings"
//    }
//}