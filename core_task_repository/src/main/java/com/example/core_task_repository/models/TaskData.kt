package com.example.core_task_repository.models

data class TaskData(
    val id: Long,
    val text: String,
    val importance: String,
    val deadline: Long,
    val isDone: Boolean,
    val createdAt: Long,
    val changedAt: Long,
    val userId: String,
    val outOfSyncNew: Boolean,
    val outOfSyncEdit: Boolean,
    val outOfSyncDelete: Boolean
)
