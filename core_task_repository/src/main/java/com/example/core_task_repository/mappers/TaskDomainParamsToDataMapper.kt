package com.example.core_task_repository.mappers

import com.example.core_domain.models.TaskDomainParams
import com.example.core_task_repository.models.TaskData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class TaskDomainParamsToDataMapper @Inject constructor(){
    fun transform(
        params: TaskDomainParams,
        createdAt: Long,
        changedAt: Long,
        outOfSyncNew: Boolean = false,
        outOfSyncEdit: Boolean = false,
        outOfSyncDelete: Boolean = false
    ): TaskData {
        return TaskData(
            id = params.id,
            text = params.text,
            importance = params.importance,
            deadline = params.deadline,
            isDone = params.isDone,
            createdAt = createdAt,
            changedAt = changedAt,
            userId = params.userId,
            outOfSyncNew = outOfSyncNew,
            outOfSyncEdit = outOfSyncEdit,
            outOfSyncDelete = outOfSyncDelete
        )
    }
}