package com.example.todoapp.mappers

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.models.TaskDomainParams

class TaskDomainParamsToDataMapper {
    fun transform(params: TaskDomainParams, createdAt: Long = 0L, changedAt: Long): TaskData {
        return TaskData(
            id = params.id,
            text = params.text,
            importance = params.importance,
            deadline = params.deadline,
            isDone = params.isDone,
            createdAt = createdAt,
            changedAt = changedAt
        )
    }
}