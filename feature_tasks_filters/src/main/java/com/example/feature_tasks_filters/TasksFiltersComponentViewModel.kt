package com.example.feature_tasks_filters

import androidx.lifecycle.ViewModel
import com.example.feature_tasks_filters.di.DaggerTasksFiltersComponent
import com.example.feature_tasks_filters.di.TasksFiltersDepsProvider

internal class TasksFiltersComponentViewModel : ViewModel() {
    val tasksFiltersComponent = DaggerTasksFiltersComponent.factory().create(
        TasksFiltersDepsProvider.deps
    )
}