package com.example.core_task_repository.mappers

import com.example.core_domain.models.TaskDomain
import com.example.core_task_repository.models.TaskData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class TaskDataToDomainMapper @Inject constructor(){
    fun transform(task: TaskData): TaskDomain {
        return TaskDomain(
            id = task.id,
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