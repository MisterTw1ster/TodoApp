package com.example.core_task_data_source.cloud.mappers

import com.example.core_task_repository.models.TaskData
import com.example.core_task_data_source.cloud.models.TaskCloud
import dagger.Reusable
import javax.inject.Inject

@Reusable
class TaskDataToCloudMapper @Inject constructor(){
    fun transform(task: TaskData): TaskCloud {
        return TaskCloud(
            id = task.id.toString(),
            text = task.text,
            importance = task.importance,
            deadline = task.deadline,
            isDone = task.isDone,
            createdAt = task.createdAt,
            changedAt = task.changedAt,
            userId = task.userId
        )
    }
}