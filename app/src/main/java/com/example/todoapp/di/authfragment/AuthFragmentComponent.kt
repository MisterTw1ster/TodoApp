package com.example.todoapp.di.authfragment

import com.example.todoapp.di.scope.AuthFragmentScope
import com.example.todoapp.presentation.auth.AuthFragment
import dagger.Subcomponent

@Subcomponent(modules = [AuthFragmentModule::class])
@AuthFragmentScope
interface AuthFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthFragmentComponent
    }

    fun inject(authFragment: AuthFragment)
}