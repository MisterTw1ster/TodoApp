package com.example.todoapp.datasource.tasks.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskCache::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}