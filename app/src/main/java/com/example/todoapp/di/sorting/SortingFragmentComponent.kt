package com.example.todoapp.di.sorting

import com.example.todoapp.di.scope.DetailsFragmentScope
import com.example.todoapp.presentation.sorting.SortingFragment
import dagger.Subcomponent

@Subcomponent(modules = [SortingFragmentModule::class])
@DetailsFragmentScope
interface SortingFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SortingFragmentComponent
    }
    fun inject(sortingFragment: SortingFragment)
}