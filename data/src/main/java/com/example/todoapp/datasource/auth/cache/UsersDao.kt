package com.example.todoapp.datasource.auth.cache

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * FROM users_table")
    suspend fun fetchUsers(): List<UserCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserCache)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editUser(user: UserCache)

//    ////////////////
//    @Query("SELECT * FROM tasks_table WHERE out_of_sync_delete == 0")
//    fun observeTasks(): Flow<List<TaskCache>>
//
//    @Query("SELECT * FROM tasks_table WHERE user_id == :userId AND id == :id")
//    suspend fun getTaskById(id: Long, userId: String): TaskCache
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addTask(task: TaskCache): Long
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun editTask(task: TaskCache)
//
//    @Query("DELETE FROM tasks_table WHERE user_id == :userId AND id = :id")
//    suspend fun deleteTaskById(id: Long, userId: String)
//
//    @Transaction
//    suspend fun replaceAll(tasks: List<TaskCache>) {
//        deleteTasks()
//        addTasks(tasks)
//    }
//
//    @Query("DELETE FROM tasks_table")
//    suspend fun deleteTasks()
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addTasks(tasks: List<TaskCache>)
//
//
//    @Query("UPDATE tasks_table SET out_of_sync_delete = 1, changed_at = :changedAt WHERE user_id == :userId AND id == :id")
//    suspend fun markOutOfSyncDeleteTaskById(id: Long, changedAt: Long, userId: String)
//
//
//    @Query("SELECT * FROM tasks_table WHERE out_of_sync_delete == 1")
//    fun fetchOutOfSyncMarkDeleteTasks(): List<TaskCache>
//
//    @Query("SELECT * FROM tasks_table WHERE out_of_sync_edit == 1 AND out_of_sync_delete == 0")
//    fun fetchOutOfSyncEditTasks(): List<TaskCache>
//
//    @Query("SELECT * FROM tasks_table WHERE out_of_sync_new == 1 AND out_of_sync_delete == 0")
//    fun fetchOutOfSyncNewTasks(): List<TaskCache>
}