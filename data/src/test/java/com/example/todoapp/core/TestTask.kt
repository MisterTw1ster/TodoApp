package com.example.todoapp.core

fun testTaskCache(
    id: Long = 1, text: String = "text",
    importance: String = "basic", deadline: Long = 0L,
    isDone: Boolean = false, createdAt: Long = 0L, changedAt: Long = 0L
): TaskCache {
    return TaskCahce(
        id = id, text = text, importance = importance, deadline = deadline,
        isDone = isDone, createdAt = createdAt, changedAt = changedAt
    )
}

//fun testTaskCloud(
//    id: Long = 1, text: String = "text",
//    importance: String = "basic", deadline: Long = 0L,
//    isDone: Boolean = false, createdAt: Long = 0L, changedAt: Long = 0L
//): TaskCloud {
//    return TaskCloud(
//        id = id, text = text, importance = importance, deadline = deadline,
//        isDone = isDone, createdAt = createdAt, changedAt = changedAt
//    )
//}

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

fun testTaskDomain(
    id: Long = 1, text: String = "text",
    importance: String = "basic", deadline: Long = 0L,
    isDone: Boolean = false, createdAt: Long = 0L, changedAt: Long = 0L
): TaskDomain {
    return TaskDomain(
        id = id, text = text, importance = importance, deadline = deadline,
        isDone = isDone, createdAt = createdAt, changedAt = changedAt
    )
}