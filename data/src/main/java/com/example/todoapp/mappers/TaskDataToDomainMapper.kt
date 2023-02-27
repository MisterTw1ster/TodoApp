package com.example.todoapp.mappers

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.models.TaskDomain

class TaskDataToDomainMapper {
    fun transform(task: TaskData): TaskDomain {
        return TaskDomain(
            id = task.id,
            text = task.text,
            importance = task.importance,
            deadline = task.deadline,
            isDone = task.isDone,
            createdAt = task.createdAt,
            changedAt = task.changedAt
        )
    }
}