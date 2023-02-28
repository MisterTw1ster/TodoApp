package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.datasource.tasks.cache.TasksDao
import com.example.todoapp.datasource.tasks.cache.TasksDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    //    @DataScope
    @DataScope
    fun getTasksDatabase(context: Context): TasksDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TasksDatabase::class.java,
            "tasks_database"
        ).build()
    }

    @Provides
//    @DataScope
    @DataScope
    fun provideTasksDao(db: TasksDatabase): TasksDao {
        return db.tasksDao()
    }

}