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

    @Query("DELETE FROM tasks_table")
    suspend fun deleteTasks()

    @Transaction
    suspend fun replaceAll(tasks: List<TaskCache>) {
        deleteTasks()
        addTasks(tasks)
    }

    @Query("DELETE FROM tasks_table WHERE id = :id")
    suspend fun deleteTask(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTasks(tasks: List<TaskCache>)


    @Query("UPDATE tasks_table SET out_of_sync_delete = 1, changed_at = :changedAt WHERE id == :id")
    suspend fun markOutOfSyncDeleteTask(id: Long, changedAt: Long)

    @Query("UPDATE tasks_table SET out_of_sync_edit = 1 WHERE id == :id")
    suspend fun markOutOfSyncEditTask(id: Long)

    @Query("UPDATE tasks_table SET out_of_sync_new = 1 WHERE id == :id")
    suspend fun markOutOfSyncNewTask(id: Long)


    @Query("SELECT * FROM tasks_table WHERE out_of_sync_delete == 1")
    fun fetchOutOfSyncMarkDeleteTasks(): List<TaskCache>

    @Query("SELECT * FROM tasks_table WHERE out_of_sync_edit == 1 AND out_of_sync_delete == 0")
    fun fetchOutOfSyncEditTasks(): List<TaskCache>

    @Query("SELECT * FROM tasks_table WHERE out_of_sync_new == 1 AND out_of_sync_delete == 0")
    fun fetchOutOfSyncNewTasks(): List<TaskCache>
}