package com.example.feature_dialogs.taskSorting.viewModel

import androidx.lifecycle.ViewModel
import com.example.feature_dialogs.taskSorting.di.DaggerTaskSortingComponent
import com.example.feature_dialogs.taskSorting.di.TaskSortingDepsProvider

internal class TaskSortingComponentViewModel: ViewModel() {
    val component = DaggerTaskSortingComponent.factory().create(TaskSortingDepsProvider.deps)
}