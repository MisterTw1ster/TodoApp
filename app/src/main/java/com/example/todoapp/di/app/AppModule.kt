package com.example.todoapp.di.app

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.todoapp.di.DataBindModule
import com.example.todoapp.di.DatabaseModule
import com.example.todoapp.di.DomainBindModule
import com.example.todoapp.di.NetworkModule
import com.example.todoapp.di.authfragment.AuthFragmentComponent
import com.example.todoapp.di.detailsfragment.DetailsFragmentComponent
import com.example.todoapp.di.scope.AppScope
import com.example.todoapp.di.taskfragment.TasksFragmentComponent
import dagger.Module
import dagger.Provides

@Module(
    includes = [DatabaseModule::class, NetworkModule::class,
        DataBindModule::class, DomainBindModule::class],
    subcomponents = [AuthFragmentComponent::class,
        TasksFragmentComponent::class, DetailsFragmentComponent::class]
)
class AppModule {
    @Provides
    @AppScope
    fun provideAppContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @AppScope
    fun provideConnectivityManager(appContext: Context): ConnectivityManager {
        return appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}