package com.example.todoapp.di.app

import android.app.Application
import com.example.todoapp.App
import com.example.todoapp.di.DataScope
import com.example.todoapp.di.DomainScope
import com.example.todoapp.di.authfragment.AuthFragmentComponent
import com.example.todoapp.di.authfragment.SelectUserFragmentComponent
import com.example.todoapp.di.detailsfragment.DetailsFragmentComponent
import com.example.todoapp.di.filters.FiltersFragmentComponent
import com.example.todoapp.di.mainactivity.MainActivityComponent
import com.example.todoapp.di.scope.AppScope
import com.example.todoapp.di.taskfragment.TasksFragmentComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
@AppScope
@DomainScope
@DataScope
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: Application
        ): AppComponent
    }
    fun inject(app: App)
    fun mainActivityComponent(): MainActivityComponent.Factory
    fun selectUserFragmentComponent(): SelectUserFragmentComponent.Factory
    fun authFragmentComponent(): AuthFragmentComponent.Factory
    fun tasksFragmentComponent(): TasksFragmentComponent.Factory
    fun detailsFragmentComponent(): DetailsFragmentComponent.Factory
    fun filtersFragmentComponent(): FiltersFragmentComponent.Factory

}