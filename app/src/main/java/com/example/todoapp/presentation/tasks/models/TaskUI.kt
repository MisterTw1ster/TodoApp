package com.example.todoapp.presentation.tasks.models

import com.example.todoapp.presentation.common.ItemList

data class TaskUI(
    val id: Long,
    val text: String,
    val importance: String,
    val deadline: Long,
    val isDone: Boolean,
    val color: Int,
    val deadlineText: String?,
    val userId: String
): ItemList {
    companion object {
        const val NEW_TASK_ID = 0L
    }
}
