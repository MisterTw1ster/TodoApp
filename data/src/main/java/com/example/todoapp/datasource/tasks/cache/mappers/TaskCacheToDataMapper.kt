package com.example.todoapp.datasource.tasks.cache.mappers

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.tasks.cache.TaskCache

class TaskCacheToDataMapper {
    fun transform(task: TaskCache): TaskData {
        return TaskData(
            id = task.id,
            text = task.text,
            importance = task.importance,
            deadline = task.deadline,
            isDone = task.isDone,
            createdAt = task.createdAt,
            changedAt = task.changedAt,
        )
    }
}