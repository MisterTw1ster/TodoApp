package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.datasource.auth.cache.UsersDao
import com.example.todoapp.datasource.auth.cache.UsersDatabase
import com.example.todoapp.datasource.tasks.cache.TasksDao
import com.example.todoapp.datasource.tasks.cache.TasksDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    @DataScope
    fun getTasksDatabase(context: Context): TasksDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TasksDatabase::class.java,
            "tasks_database"
        ).build()
    }

    @Provides
    @DataScope
    fun getUsersDatabase(context: Context): UsersDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UsersDatabase::class.java,
            "users_database"
        ).build()
    }

    @Provides
    @DataScope
    fun provideTasksDao(db: TasksDatabase): TasksDao {
        return db.tasksDao()
    }

    @Provides
    @DataScope
    fun provideUsersDao(db: UsersDatabase): UsersDao {
        return db.usersDao()
    }

}