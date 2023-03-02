package com.example.todoapp.datasource.tasks.cache

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks_table WHERE delete_after_sync == 0")
    fun observeTasks(): Flow<List<TaskCache>>

    @Query("SELECT * FROM tasks_table WHERE id == :id")
    suspend fun getTaskById(id: Long): TaskCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: TaskCache): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editTask(task: TaskCache)

    @Query("DELETE FROM tasks_table WHERE id = :id")
    suspend fun deleteTask(id: Long)


    @Query("UPDATE tasks_table SET is_sync = 1 WHERE id == :id")
    suspend fun markIsSyncTask(id: Long)

    @Query("UPDATE tasks_table SET delete_after_sync = 1 WHERE id == :id")
    suspend fun markDeleteAfterSyncTask(id: Long)


    @Query("SELECT * FROM tasks_table WHERE delete_after_sync == 1")
    fun fetchOutOfSyncMarkDeleteTasks(): List<TaskCache>

    @Query("SELECT * FROM tasks_table WHERE is_sync == 0")
    fun fetchOutOfSyncTasks(): List<TaskCache>
}