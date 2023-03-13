package com.example.todoapp.datasource.tasks.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class TaskCache(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "importance") val importance: String,
    @ColumnInfo(name = "deadline") val deadline: Long,
    @ColumnInfo(name = "is_done") val isDone: Boolean,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "changed_at") val changedAt: Long,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "out_of_sync_new") val outOfSyncNew: Boolean = false,
    @ColumnInfo(name = "out_of_sync_edit") val outOfSyncEdit: Boolean = false,
    @ColumnInfo(name = "out_of_sync_delete") val outOfSyncDelete: Boolean = false
)
