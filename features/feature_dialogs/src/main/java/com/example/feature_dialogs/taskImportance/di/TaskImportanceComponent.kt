package com.example.feature_dialogs.taskImportance.di

import com.example.feature_dialogs.taskImportance.view.TaskImportanceDialogFragment
import dagger.Component

@TaskImportanceScope
@Component(
    dependencies = [TaskImportanceDeps::class]
)
interface TaskImportanceComponent {

    fun inject(fragment: TaskImportanceDialogFragment)

    @Component.Factory
    interface Factory {
        fun create(
            deps: TaskImportanceDeps
        ): TaskImportanceComponent
    }

}