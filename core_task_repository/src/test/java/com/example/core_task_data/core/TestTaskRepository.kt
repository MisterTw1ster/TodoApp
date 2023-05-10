package com.example.core_task_data.core

import com.example.core_domain.models.TaskDomain
import com.example.core_domain.models.TaskDomainParams
import com.example.core_task_repository.models.TaskData

fun testTaskData(
    id: Long = 1, text: String = "text", importance: String = "basic",
    deadline: Long = 0L, isDone: Boolean = false, createdAt: Long = 0L,
    changedAt: Long = 0L, outOfSyncNew: Boolean = false,
    outOfSyncEdit: Boolean = false, outOfSyncDelete: Boolean = false,
    userId: String = "id1"
): TaskData {
    return TaskData(
        id = id, text = text, importance = importance, deadline = deadline, isDone = isDone,
        createdAt = createdAt, changedAt = changedAt, outOfSyncNew = outOfSyncNew,
        outOfSyncEdit = outOfSyncEdit, outOfSyncDelete = outOfSyncDelete, userId = userId
    )
}

fun testTaskDomain(
    id: Long = 1, text: String = "text",
    importance: String = "basic", deadline: Long = 0L,
    isDone: Boolean = false, createdAt: Long = 0L, changedAt: Long = 0L,
    userId: String = "id1"
): TaskDomain {
    return TaskDomain(
        id = id, text = text, importance = importance, deadline = deadline,
        isDone = isDone, createdAt = createdAt, changedAt = changedAt, userId = userId
    )
}

fun testTaskDomainParams(
    id: Long = 1, text: String = "text",
    importance: String = "basic", deadline: Long = 0L,
    isDone: Boolean = false, userId: String = "id1"
): TaskDomainParams {
    return TaskDomainParams(
        id = id, text = text, importance = importance, deadline = deadline,
        isDone = isDone, userId = userId
    )
}