package com.example.feature_dialogs.taskSorting.di

import kotlin.properties.Delegates.notNull

interface TaskSortingDepsProvider {
    val deps: TaskSortingDeps
    companion object: TaskSortingDepsProvider by TaskSortingDepsStore
}

object TaskSortingDepsStore: TaskSortingDepsProvider {
    override var deps: TaskSortingDeps by notNull()
}