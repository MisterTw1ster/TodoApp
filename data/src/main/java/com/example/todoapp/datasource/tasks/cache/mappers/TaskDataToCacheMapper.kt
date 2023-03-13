package com.example.todoapp.datasource.tasks.cache.mappers

import com.example.todoapp.datasource.tasks.cache.TaskCache
import com.example.todoapp.models.TaskData
import javax.inject.Inject

class TaskDataToCacheMapper @Inject constructor(){
    fun transform(task: TaskData): TaskCache {
        return TaskCache(
            id = task.id,
            text = task.text,
            importance = task.importance,
            deadline = task.deadline,
            isDone = task.isDone,
            createdAt = task.createdAt,
            changedAt = task.changedAt,
            userId = task.userId,
            outOfSyncNew = task.outOfSyncNew,
            outOfSyncEdit = task.outOfSyncEdit,
            outOfSyncDelete = task.outOfSyncDelete
        )
    }
}