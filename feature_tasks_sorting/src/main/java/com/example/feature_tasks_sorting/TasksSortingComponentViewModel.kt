package com.example.feature_tasks_sorting

import androidx.lifecycle.ViewModel
import com.example.feature_tasks_sorting.di.DaggerTasksSortingComponent
import com.example.feature_tasks_sorting.di.TasksSortingDepsProvider

internal class TasksSortingComponentViewModel: ViewModel() {
    val tasksSortingComponent = DaggerTasksSortingComponent.factory().create(TasksSortingDepsProvider.deps)
}