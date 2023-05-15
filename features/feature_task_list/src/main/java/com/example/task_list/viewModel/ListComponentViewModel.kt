package com.example.task_list.viewModel

import androidx.lifecycle.ViewModel
import com.example.task_list.di.DaggerListComponent
import com.example.task_list.di.ListDepsProvider

internal class ListComponentViewModel: ViewModel() {
    val component = DaggerListComponent.factory().create(ListDepsProvider.deps)
}