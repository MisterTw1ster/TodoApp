package com.example.todoapp.di.taskfragment

import com.example.todoapp.di.scope.TasksFragmentScope
import com.example.todoapp.presentation.tasks.TasksFragment
import dagger.Subcomponent

@Subcomponent(modules = [TasksFragmentModule::class])
@TasksFragmentScope
interface TasksFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): TasksFragmentComponent
    }

    fun inject(tasksFragment: TasksFragment)
}