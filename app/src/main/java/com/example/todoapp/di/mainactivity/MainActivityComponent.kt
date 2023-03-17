package com.example.todoapp.di.mainactivity

import com.example.todoapp.di.scope.MainActivityScope
import com.example.todoapp.presentation.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityModule::class])
@MainActivityScope
interface MainActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}