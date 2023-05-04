package com.example.todoapp.di.app

import com.example.components.presentation.navigation.NavigationImpl
import com.example.core.navigation.Navigation
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {

    @Binds
    fun bindNavigationImpl_to_Navigation(
        navigationImpl: NavigationImpl
    ): Navigation

}