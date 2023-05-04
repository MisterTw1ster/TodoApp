package com.example.core_domain.models

data class TaskDomain(
    val id: Long,
    val text: String,
    val importance: String,
    val deadline: Long,
    val isDone: Boolean,
    val createdAt: Long,
    val changedAt: Long,
    val userId: String,
)