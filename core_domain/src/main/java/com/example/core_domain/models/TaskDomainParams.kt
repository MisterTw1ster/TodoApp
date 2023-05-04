package com.example.core_domain.models

data class TaskDomainParams(
    val id: Long,
    val text: String,
    val importance: String,
    val deadline: Long,
    val isDone: Boolean,
    val userId: String
)
