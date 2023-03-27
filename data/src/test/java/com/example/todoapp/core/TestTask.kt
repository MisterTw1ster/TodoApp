//package com.example.todoapp.core
//
//import com.example.todoapp.datasource.tasks.cache.TaskCache
//import com.example.todoapp.datasource.tasks.cloud.TaskCloud
//import com.example.todoapp.models.TaskData
//import com.example.todoapp.models.TaskDomain
//import com.example.todoapp.models.TaskDomainParams
//
//fun testTaskCache(
//    id: Long = 1, text: String = "text", importance: String = "basic",
//    deadline: Long = 0L, isDone: Boolean = false, createdAt: Long = 0L,
//    changedAt: Long = 0L, outOfSyncNew: Boolean = false,
//    outOfSyncEdit: Boolean = false, outOfSyncDelete: Boolean = false
//): TaskCache {
//    return TaskCache(
//        id = id, text = text, importance = importance, deadline = deadline, isDone = isDone,
//        createdAt = createdAt, changedAt = changedAt, outOfSyncNew = outOfSyncNew,
//        outOfSyncEdit = outOfSyncEdit, outOfSyncDelete = outOfSyncDelete
//    )
//}
//
//fun testTaskCloud(
//    id: String = "1", text: String = "text",
//    importance: String = "basic", deadline: Long = 0L,
//    isDone: Boolean = false, createdAt: Long = 0L, changedAt: Long = 0L
//): TaskCloud {
//    return TaskCloud(
//        id = id, text = text, importance = importance, deadline = deadline,
//        isDone = isDone, createdAt = createdAt, changedAt = changedAt
//    )
//}
//
//fun testTaskData(
//    id: Long = 1, text: String = "text", importance: String = "basic",
//    deadline: Long = 0L, isDone: Boolean = false, createdAt: Long = 0L,
//    changedAt: Long = 0L, outOfSyncNew: Boolean = false,
//    outOfSyncEdit: Boolean = false, outOfSyncDelete: Boolean = false
//): TaskData {
//    return TaskData(
//        id = id, text = text, importance = importance, deadline = deadline, isDone = isDone,
//        createdAt = createdAt, changedAt = changedAt, outOfSyncNew = outOfSyncNew,
//        outOfSyncEdit = outOfSyncEdit, outOfSyncDelete = outOfSyncDelete
//    )
//}
//
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
//
//fun testTaskDomainParams(
//    id: Long = 1, text: String = "text",
//    importance: String = "basic", deadline: Long = 0L,
//    isDone: Boolean = false
//): TaskDomainParams {
//    return TaskDomainParams(
//        id = id, text = text, importance = importance, deadline = deadline,
//        isDone = isDone
//    )
//}