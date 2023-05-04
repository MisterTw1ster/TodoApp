package com.example.core_task_data_source.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core_task_data_source.cache.models.TaskCache

@Database(entities = [TaskCache::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}