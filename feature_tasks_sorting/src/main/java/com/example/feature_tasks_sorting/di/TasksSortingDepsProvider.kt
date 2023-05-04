package com.example.feature_tasks_sorting.di

import kotlin.properties.Delegates.notNull

interface TasksSortingDepsProvider {
    val deps: TasksSortingDeps
    companion object: TasksSortingDepsProvider by TasksSortingDepsStore
}

object TasksSortingDepsStore: TasksSortingDepsProvider {
    override var deps: TasksSortingDeps by notNull()
}