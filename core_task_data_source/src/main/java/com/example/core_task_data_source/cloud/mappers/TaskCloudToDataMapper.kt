package com.example.core_task_data_source.cloud.mappers

import com.example.core_task_repository.models.TaskData
import com.example.core_task_data_source.cloud.models.TaskCloud
import dagger.Reusable
import javax.inject.Inject

@Reusable
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