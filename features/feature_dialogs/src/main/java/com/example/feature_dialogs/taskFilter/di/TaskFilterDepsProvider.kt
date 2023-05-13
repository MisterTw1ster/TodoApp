package com.example.feature_dialogs.taskFilter.di

import kotlin.properties.Delegates.notNull

interface TaskFilterDepsProvider {
    val deps: TaskFilterDeps
    companion object: TaskFilterDepsProvider by TaskFilterDepsStore
}

object TaskFilterDepsStore: TaskFilterDepsProvider {
    override var deps: TaskFilterDeps by notNull()
}