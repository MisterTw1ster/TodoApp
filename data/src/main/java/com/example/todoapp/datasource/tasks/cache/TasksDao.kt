package com.example.todoapp.datasource.tasks.cache

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks_table WHERE out_of_sync_delete == 0")
    fun observeTasks(): Flow<List<TaskCache>>

    @Query("SELECT * FROM tasks_table WHERE id == :id")
    suspend fun getTaskById(id: Long): TaskCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: TaskCache): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editTask(task: TaskCache)

    @Query("DELETE FROM tasks_table WHERE id = :id")
    suspend fun deleteTaskById(id: Long)

    @Transaction
    suspend fun replaceAll(tasks: List<TaskCache>, userId: String) {
        deleteTasks(userId)
        addTasks(tasks)
    }

    @Query("DELETE FROM tasks_table WHERE user_id == :userId")
    suspend fun deleteTasks(userId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTasks(tasks: List<TaskCache>)


    @Query("UPDATE tasks_table SET out_of_sync_delete = 1, changed_at = :changedAt WHERE id == :id")
    suspend fun markOutOfSyncDeleteTaskById(id: Long, changedAt: Long)


    @Query("SELECT user_id FROM tasks_table WHERE out_of_sync_new == 1 OR out_of_sync_edit == 1 OR out_of_sync_delete == 1 GROUP BY user_id")
    fun fetchUsersIdOutOfSyncTasks(): List<String>

    @Query("SELECT * FROM tasks_table WHERE user_id == :userId AND out_of_sync_delete == 1")
    fun fetchOutOfSyncMarkDeleteTasks(userId: String): List<TaskCache>

    @Query("SELECT * FROM tasks_table WHERE user_id == :userId AND out_of_sync_edit == 1 AND out_of_sync_delete == 0")
    fun fetchOutOfSyncEditTasks(userId: String): List<TaskCache>

    @Query("SELECT * FROM tasks_table WHERE user_id == :userId AND out_of_sync_new == 1 AND out_of_sync_delete == 0")
    fun fetchOutOfSyncNewTasks(userId: String): List<TaskCache>
}