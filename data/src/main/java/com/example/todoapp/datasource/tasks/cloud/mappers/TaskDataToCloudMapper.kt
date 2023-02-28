package com.example.todoapp.datasource.tasks.cloud.mappers

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.tasks.cloud.TaskCloud
import javax.inject.Inject

class TaskDataToCloudMapper @Inject constructor(){
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