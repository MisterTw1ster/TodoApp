package com.example.feature_tasks_sorting.di

import com.example.feature_tasks_sorting.TasksSortingFragment
import dagger.Component

@TasksSortingScope
@Component(
    modules = [TasksSortingModule::class],
    dependencies = [TasksSortingDeps::class]
)
internal interface TasksSortingComponent {

    fun inject(fragment: TasksSortingFragment)

    @Component.Factory
    interface Factory {
        fun create(
            deps: TasksSortingDeps
        ): TasksSortingComponent
    }
}