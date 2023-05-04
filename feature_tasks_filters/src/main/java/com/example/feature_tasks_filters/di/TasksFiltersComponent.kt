package com.example.feature_tasks_filters.di

import com.example.feature_tasks_filters.TasksFiltersFragment
import dagger.Component

@TasksFiltersScope
@Component(
    modules = [TasksFiltersModule::class],
    dependencies = [TasksFiltersDeps::class]
)
internal interface TasksFiltersComponent {

    fun inject(fragment: TasksFiltersFragment)

    @Component.Factory
    interface Factory {
        fun create(
            deps: TasksFiltersDeps
        ): TasksFiltersComponent
    }
}