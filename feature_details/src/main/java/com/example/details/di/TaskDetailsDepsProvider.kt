package com.example.details.di

import kotlin.properties.Delegates.notNull

interface TaskDetailsDepsProvider {
    val deps: TaskDetailsDeps
    companion object: TaskDetailsDepsProvider by TaskDetailsDepsStore
}

object TaskDetailsDepsStore: TaskDetailsDepsProvider {
    override var deps: TaskDetailsDeps by notNull()
}