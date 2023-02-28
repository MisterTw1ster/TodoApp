package com.example.todoapp

import android.app.Application
import android.content.Context
import com.example.todoapp.di.app.AppComponent
import com.example.todoapp.di.app.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }


//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
