package com.example.core_task_data_source.core

import com.example.core_task_data_source.cache.models.TaskCache
import com.example.core_task_data_source.cloud.models.TaskCloud
import com.example.core_task_repository.models.TaskData

fun testTaskCache(
    id: Long = 1, text: String = "text", importance: String = "basic",
    deadline: Long = 0L, isDone: Boolean = false, createdAt: Long = 0L,
    changedAt: Long = 0L, outOfSyncNew: Boolean = false,
    outOfSyncEdit: Boolean = false, outOfSyncDelete: Boolean = false,
    userId: String = "id1"
): TaskCache {
    return TaskCache(
        id = id, text = text, importance = importance, deadline = deadline, isDone = isDone,
        createdAt = createdAt, changedAt = changedAt, outOfSyncNew = outOfSyncNew,
        outOfSyncEdit = outOfSyncEdit, outOfSyncDelete = outOfSyncDelete, userId = userId
    )
}

fun testTaskCloud(
    id: String = "1", text: String = "text",
    importance: String = "basic", deadline: Long = 0L,
    isDone: Boolean = false, createdAt: Long = 0L, changedAt: Long = 0L,
    userId: String = "id1"
): TaskCloud {
    return TaskCloud(
        id = id, text = text, importance = importance, deadline = deadline,
        isDone = isDone, createdAt = createdAt, changedAt = changedAt, userId = userId
    )
}

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
