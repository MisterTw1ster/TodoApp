package com.example.todoapp.datasource.tasks.cloud.mappers

import com.example.todoapp.models.TaskData
import com.example.todoapp.datasource.tasks.cloud.TaskCloud
import javax.inject.Inject

class TaskCloudToDataMapper @Inject constructor(){
    fun transform(task: TaskCloud, userId: String): TaskData {
        return TaskData(
            id = task.id.toLong(),
            text = task.text,
            importance = task.importance,
            deadline = task.deadline,
            isDone = task.isDone,
            createdAt = task.createdAt,
            changedAt = task.changedAt,
            userId = userId,
            outOfSyncNew = false,
            outOfSyncEdit = false,
            outOfSyncDelete = false
        )
    }
}