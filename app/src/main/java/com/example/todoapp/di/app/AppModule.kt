package com.example.todoapp.di.app

import android.app.Application
import android.content.Context
import com.example.todoapp.di.DataBindModule
import com.example.todoapp.di.DatabaseModule
import com.example.todoapp.di.DomainBindModule
import com.example.todoapp.di.NetworkModule
import com.example.todoapp.di.detailsFragment.DetailsFragmentComponent
import com.example.todoapp.di.scope.AppScope
import com.example.todoapp.di.taskfragment.TasksFragmentComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [DatabaseModule::class, NetworkModule::class, DataBindModule::class, DomainBindModule::class],
    subcomponents = [TasksFragmentComponent::class, DetailsFragmentComponent::class]
)
class AppModule {
    @Provides
    @AppScope
    fun provideAppContext(app: Application): Context {
        return app.applicationContext
    }
}