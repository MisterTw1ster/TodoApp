package com.example.todoapp.presentation.tasks.models

import com.example.todoapp.presentation.tasks.adapter.Item

data class TaskUI(
    val id: Long,
    val text: String,
    val importance: String,
    val deadline: Long,
    val isDone: Boolean,
    val color: Int,
    val deadlineText: String?,
    val userId: String
): Item
