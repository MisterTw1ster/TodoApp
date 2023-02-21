package com.example.todoapp.datasource.tasks.cloud.mappers

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.tasks.cloud.TaskCloud

class TaskCloudToDataMapper {
    fun transform(task: TaskCloud): TaskData {
        return TaskData(
            id = task.id.toLong(),
            text = task.text,
            importance = task.importance,
            deadline = task.deadline,
            isDone = task.isDone,
            createdAt = task.createdAt,
            changedAt = task.changedAt
        )
    }
}