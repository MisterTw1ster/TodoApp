package com.example.details.di

import com.example.details.TaskDetailsFragment
import dagger.Component

@TaskDetailsScope
@Component(
    modules = [TaskDetailsModule::class],
    dependencies = [TaskDetailsDeps::class]
)
internal interface TaskDetailsComponent {

    fun inject(fragment: TaskDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            deps: TaskDetailsDeps
        ): TaskDetailsComponent
    }
}