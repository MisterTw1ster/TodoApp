package com.example.feature_dialogs.taskImportance.di

import kotlin.properties.Delegates

interface TaskImportanceDepsProvider {
    val deps: TaskImportanceDeps
    companion object: TaskImportanceDepsProvider by TaskImportanceDepsStore
}

object TaskImportanceDepsStore: TaskImportanceDepsProvider {
    override var deps: TaskImportanceDeps by Delegates.notNull()
}