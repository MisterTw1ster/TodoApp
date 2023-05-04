package com.example.core_task_data_source.di

import android.content.Context
import androidx.room.Room
import com.example.core.di.scope.AppScope
import com.example.core_task_data_source.cache.TasksDao
import com.example.core_task_data_source.cache.TasksDatabase
import dagger.Module
import dagger.Provides

@Module
class TasksDatabaseModule {

    @Provides
    @AppScope
    fun getTasksDatabase(context: Context): TasksDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TasksDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @AppScope
    fun provideTasksDao(db: TasksDatabase): TasksDao {
        return db.tasksDao()
    }

    companion object {
        private const val DATABASE_NAME = "tasks_database"
    }
}