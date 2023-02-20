package com.example.todoapp.datasource.tasks.cache

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks_table")
    fun observeTasks(): Flow<List<TaskCache>>

    @Query("SELECT * FROM tasks_table WHERE id == :taskID")
    suspend fun getTaskById(taskID: Long): TaskCache

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: TaskCache): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editTask(task: TaskCache)

}