package com.example.feature_dialogs.taskFilter.di

import com.example.feature_dialogs.taskFilter.view.TaskFilterDialogFragment
import dagger.Component

@TaskFilterScope
@Component(
    modules = [TaskFilterModule::class],
    dependencies = [TaskFilterDeps::class]
)
internal interface TaskFilterComponent {

    fun inject(fragment: TaskFilterDialogFragment)

    @Component.Factory
    interface Factory {
        fun create(
            deps: TaskFilterDeps
        ): TaskFilterComponent
    }
}