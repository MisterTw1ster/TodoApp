package com.example.todoapp.di.detailsfragment

import com.example.todoapp.di.scope.DetailsFragmentScope
import com.example.todoapp.presentation.details.DetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailsFragmentModule::class])
@DetailsFragmentScope
interface DetailsFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsFragmentComponent
    }

    fun inject(taskDetailsFragment: DetailsFragment)
}