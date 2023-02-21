package com.example.todoapp.datasource.tasks.cloud.mappers

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.tasks.cloud.TaskCloud

class TaskDataToCloudMapper {
    fun transform(task: TaskData): TaskCloud {
        return TaskCloud(
            id = task.id.toString(),
            text = task.text,
            importance = task.importance,
            deadline = task.deadline,
            isDone = task.isDone,
            createdAt = task.createdAt,
            changedAt = task.changedAt
        )
    }
}