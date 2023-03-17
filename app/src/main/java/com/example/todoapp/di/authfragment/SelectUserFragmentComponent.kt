package com.example.todoapp.di.authfragment

import com.example.todoapp.di.scope.SelectUserFragmentScope
import com.example.todoapp.presentation.auth.SelectUserFragment
import dagger.Subcomponent

@Subcomponent(modules = [SelectUserFragmentModule::class])
@SelectUserFragmentScope
interface SelectUserFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SelectUserFragmentComponent
    }

    fun inject(selectUserFragment: SelectUserFragment)
}