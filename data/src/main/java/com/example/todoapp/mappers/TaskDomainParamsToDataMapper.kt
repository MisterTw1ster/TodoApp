package com.example.todoapp.mappers

import com.example.todoapp.models.TaskData
import com.example.todoapp.models.TaskDomainParams
import javax.inject.Inject

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
            outOfSyncNew = outOfSyncNew,
            outOfSyncEdit = outOfSyncEdit,
            outOfSyncDelete = outOfSyncDelete
        )
    }
}