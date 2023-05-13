package com.example.feature_dialogs.taskFilter.viewModel

import androidx.lifecycle.ViewModel
import com.example.feature_dialogs.taskFilter.di.DaggerTaskFilterComponent
import com.example.feature_dialogs.taskFilter.di.TaskFilterDepsProvider

internal class TaskFilterComponentViewModel : ViewModel() {
    val component = DaggerTaskFilterComponent.factory().create(
        TaskFilterDepsProvider.deps
    )
}

