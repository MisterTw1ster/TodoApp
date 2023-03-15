package com.example.todoapp.datasource.auth.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserCache::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}