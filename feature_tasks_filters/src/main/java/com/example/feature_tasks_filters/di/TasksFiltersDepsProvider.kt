package com.example.feature_tasks_filters.di

import kotlin.properties.Delegates.notNull

interface TasksFiltersDepsProvider {
    val deps: TasksFiltersDeps
    companion object: TasksFiltersDepsProvider by TasksFiltersDepsStore
}

object TasksFiltersDepsStore: TasksFiltersDepsProvider {
    override var deps: TasksFiltersDeps by notNull()
}