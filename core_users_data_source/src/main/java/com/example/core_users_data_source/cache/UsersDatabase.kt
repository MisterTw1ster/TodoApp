package com.example.core_users_data_source.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core_users_data_source.cache.models.UserCache
import com.example.core_users_data_source.cache.models.UsersDao

@Database(entities = [UserCache::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}