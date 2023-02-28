package com.example.todoapp.di.app

import android.app.Application
import com.example.todoapp.App
import com.example.todoapp.di.scope.AppScope
import com.example.todoapp.di.taskfragment.TasksFragmentComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: Application
        ): AppComponent
    }
    fun inject(app: App)
    fun tasksFragmentComponent(): TasksFragmentComponent.Factory
//    fun taskDetailsComponent(): DetailsFragmentComponent.Factory
}