package com.example.todoapp.models

data class TaskDataParams(
    val text: String,
    val importance: String,
    val deadline: Long,
    val isDone: Boolean,
    val time: Long = System.currentTimeMillis()
)
