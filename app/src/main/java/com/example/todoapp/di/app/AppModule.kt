package com.example.todoapp.di.app

import android.app.Application
import android.content.Context
import com.example.components.di.mainactivity.MainActivityComponent
import com.example.components.di.settings.SettingsBindsModule
import com.example.components.di.tasks.TasksBindsModule
import com.example.components.di.users.UsersBindsModule
import com.example.todoapp.presentation.navigation.CommunicationNavigation
import com.example.components.presentation.navigation.NavigationImpl
import com.example.components.presentation.navigation.SingleLiveEvent
import com.example.core.ManageResources
import com.example.core.di.scope.AppScope
import com.example.core_settings_data_source.di.SettingsDataBaseModule
import com.example.core_task_data_source.di.TasksDatabaseModule
import com.example.core_task_data_source.di.TasksNetworkModule
import com.example.core_users_data_source.di.UsersDatabaseModule
import com.example.core_users_data_source.di.UsersNetworkModule
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(
    includes = [
        TasksNetworkModule::class, TasksDatabaseModule::class, TasksBindsModule::class,
        UsersNetworkModule::class, UsersDatabaseModule::class, UsersBindsModule::class,
        SettingsDataBaseModule::class, SettingsBindsModule::class],
    subcomponents = [MainActivityComponent::class]
)
class AppModule {

    @Provides
    @AppScope
    fun provideAppContext(app: Application): Context {
        return app.applicationContext
    }

    @AppScope
    @Provides
    fun provideNavigationCommunication(): CommunicationNavigation {
        return CommunicationNavigation(SingleLiveEvent())
    }

    @AppScope
    @Provides
    fun provideNavigation(communicationNavigation: CommunicationNavigation): NavigationImpl {
        return NavigationImpl(communicationNavigation)
    }

    @Provides
    @Reusable
    fun provideManageResources(context: Context) = ManageResources(context)
}
