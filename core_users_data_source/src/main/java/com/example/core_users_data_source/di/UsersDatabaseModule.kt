package com.example.core_users_data_source.di

import android.content.Context
import androidx.room.Room
import com.example.core.di.scope.AppScope
import com.example.core_users_data_source.cache.UsersDatabase
import com.example.core_users_data_source.cache.models.UsersDao
import dagger.Module
import dagger.Provides

@Module
class UsersDatabaseModule {

    @Provides
    @AppScope
    fun getUsersDatabase(context: Context): UsersDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UsersDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @AppScope
    fun provideUsersDao(db: UsersDatabase): UsersDao {
        return db.usersDao()
    }

    companion object {
        private const val DATABASE_NAME = "users_database"
    }
}