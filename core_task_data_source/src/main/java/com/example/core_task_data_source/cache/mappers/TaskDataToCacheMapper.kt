package com.example.core_task_data_source.cache.mappers

import com.example.core_task_data_source.cache.models.TaskCache
import com.example.core_task_repository.models.TaskData
import dagger.Reusable
import javax.inject.Inject

@Reusable
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