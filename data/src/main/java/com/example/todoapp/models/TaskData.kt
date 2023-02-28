package com.example.todoapp.models

data class TaskData(
    val id: Long,
    val text: String,
    val importance: String,
    val deadline: Long,
    val isDone: Boolean,
    val createdAt: Long = 0L,
    val changedAt: Long = 0L
)
