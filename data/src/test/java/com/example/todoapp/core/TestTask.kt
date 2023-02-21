package com.example.todoapp.core

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.datasource.TaskDataParams
import com.example.todoapp.datasource.tasks.cache.TaskCache
import com.example.todoapp.datasource.tasks.cloud.TaskCloud

fun testTaskCache(
    id: Long = 1, text: String = "text",
    importance: String = "basic", deadline: Long = 0L,
    isDone: Boolean = false, createdAt: Long = 0L, changedAt: Long = 0L
): TaskCache {
    return TaskCache(
        id = id, text = text, importance = importance, deadline = deadline,
        isDone = isDone, createdAt = createdAt, changedAt = changedAt
    )
}

fun testTaskDataParams(
    text: String = "text", importance: String = "basic",
    deadline: Long = 0L, isDone: Boolean = false, time: Long = 0L
): TaskDataParams {
    return TaskDataParams(
        text = text, importance = importance, deadline = deadline,
        isDone = isDone, time = time
    )
}

fun testTaskCloud(
    id: String = "1", text: String = "text",
    importance: String = "basic", deadline: Long = 0L,
    isDone: Boolean = false, createdAt: Long = 0L, changedAt: Long = 0L
): TaskCloud {
    return TaskCloud(
        id = id, text = text, importance = importance, deadline = deadline,
        isDone = isDone, createdAt = createdAt, changedAt = changedAt
    )
}

fun testTaskData(
    id: Long = 1, text: String = "text",
    importance: String = "basic", deadline: Long = 0L,
    isDone: Boolean = false, createdAt: Long = 0L, changedAt: Long = 0L
): TaskData {
    return TaskData(
        id = id, text = text, importance = importance, deadline = deadline,
        isDone = isDone, createdAt = createdAt, changedAt = changedAt
    )
}

//fun testTaskDomain(
//    id: Long = 1, text: String = "text",
//    importance: String = "basic", deadline: Long = 0L,
//    isDone: Boolean = false, createdAt: Long = 0L, changedAt: Long = 0L
//): TaskDomain {
//    return TaskDomain(
//        id = id, text = text, importance = importance, deadline = deadline,
//        isDone = isDone, createdAt = createdAt, changedAt = changedAt
//    )
//}