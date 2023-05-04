package com.example.core_users_data_source.cache.models

import androidx.room.*

@Dao
interface UsersDao {

    @Query("SELECT * FROM users_table")
    suspend fun fetchUsers(): List<UserCache>

    @Query("SELECT * FROM users_table WHERE local_id == :id")
    suspend fun getUser(id: String): UserCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserCache)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editUser(user: UserCache)

}