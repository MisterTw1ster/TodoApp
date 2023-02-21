package com.example.todoapp.datasource.tasks.cache.mappers

import com.example.todoapp.datasource.TaskDataParams
import com.example.todoapp.datasource.tasks.cache.TaskCache

class TaskDataParamsToCacheMapper {
    fun transform(id: Long = 0L, params: TaskDataParams, createdAt: Long, changedAt: Long): TaskCache {
        return TaskCache(
            id = id,
            text = params.text,
            importance = params.importance,
            deadline = params.deadline,
            isDone = params.isDone,
            createdAt = createdAt,
            changedAt = changedAt
        )
    }
}