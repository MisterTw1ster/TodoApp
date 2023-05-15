package com.example.task_list.di

import com.example.task_list.view.ListFragment
import dagger.Component

@ListScope
@Component(
    modules = [ListModule::class],
    dependencies = [ListDeps::class]
)
internal interface ListComponent {

    fun inject(fragment: ListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            deps: ListDeps
        ): ListComponent
    }
}