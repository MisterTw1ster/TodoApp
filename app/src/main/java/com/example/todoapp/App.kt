package com.example.todoapp

import android.app.Application
import android.content.Context
import com.example.details.di.TaskDetailsDepsStore
import com.example.feature_dialogs.taskFilter.di.TaskFilterDepsStore
import com.example.feature_dialogs.taskSorting.di.TaskSortingDepsStore
import com.example.feature_list.di.ListDepsStore
import com.example.feature_user_auth.di.UserAuthDepsStore
import com.example.feature_user_select.di.UserSelectDepsStore
import com.example.todoapp.di.app.AppComponent
import com.example.todoapp.di.app.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        ListDepsStore.deps = appComponent
        TaskDetailsDepsStore.deps = appComponent
        UserSelectDepsStore.deps = appComponent
        UserAuthDepsStore.deps = appComponent
        TaskSortingDepsStore.deps = appComponent
        TaskFilterDepsStore.deps = appComponent
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }



