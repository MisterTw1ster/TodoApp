package com.example.feature_dialogs.taskSorting.di

import com.example.feature_dialogs.taskSorting.view.TaskSortingDialogFragment
import dagger.Component

@TaskSortingScope
@Component(
    modules = [TaskSortingModule::class],
    dependencies = [TaskSortingDeps::class]
)
internal interface TaskSortingComponent {

    fun inject(fragment: TaskSortingDialogFragment)

    @Component.Factory
    interface Factory {
        fun create(
            deps: TaskSortingDeps
        ): TaskSortingComponent
    }
}