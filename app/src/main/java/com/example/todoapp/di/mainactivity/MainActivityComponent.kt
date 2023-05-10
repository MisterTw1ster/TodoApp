package com.example.todoapp.di.mainactivity

import com.example.components.presentation.MainActivity
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