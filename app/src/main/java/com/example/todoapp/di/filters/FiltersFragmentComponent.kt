package com.example.todoapp.di.filters

import com.example.todoapp.di.scope.DetailsFragmentScope
import com.example.todoapp.presentation.filters.FiltersFragment
import dagger.Subcomponent

@Subcomponent(modules = [FiltersFragmentModule::class])
@DetailsFragmentScope
interface FiltersFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FiltersFragmentComponent
    }

    fun inject(filtersFragment: FiltersFragment)
}