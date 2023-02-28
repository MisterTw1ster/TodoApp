package com.example.todoapp

import android.content.Context
import androidx.room.Room
import com.example.todoapp.datasource.tasks.cache.TasksDao
import com.example.todoapp.datasource.tasks.cache.TasksDatabase
import com.example.todoapp.repository.TasksCacheDataSource

class RoomBuild {

//    fun daoRoom(context: Context): TasksDao {
//        val db = Room.databaseBuilder(
//            context,
//            TasksDatabase::class.java,
//            "tasks_database"
//        ).build()
//        return db.tasksDao()
//    }
}