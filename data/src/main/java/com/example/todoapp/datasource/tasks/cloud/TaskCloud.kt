package com.example.todoapp.datasource.tasks.cloud

data class TaskCloud(
    val id: String,
    val text: String,
    val importance: String,
    val deadline: Long,
    val isDone: Boolean,
    val createdAt: Long,
    val changedAt: Long
)