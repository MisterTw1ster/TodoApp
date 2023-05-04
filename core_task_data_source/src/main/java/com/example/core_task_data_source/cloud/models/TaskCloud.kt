package com.example.core_task_data_source.cloud.models

data class TaskCloud(
    val id: String,
    val text: String,
    val importance: String,
    val deadline: Long,
    val isDone: Boolean,
    val createdAt: Long,
    val changedAt: Long,
    val userId: String
)