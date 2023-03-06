package com.example.todoapp

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.example.todoapp.di.app.AppComponent
import com.example.todoapp.di.app.DaggerAppComponent
import com.example.todoapp.repository.TasksRepository
import com.example.todoapp.workers.tasks.ProvidePeriodicRepository
import com.example.todoapp.workers.tasks.SyncTasksWorker
import javax.inject.Inject

class App : Application(), ProvidePeriodicRepository {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var repository: TasksRepository


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)

        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(
            SyncTasksWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            SyncTasksWorker.makeRequest()
        )
    }

    override fun providePeriodicRepository(): TasksRepository {
        return repository
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }


