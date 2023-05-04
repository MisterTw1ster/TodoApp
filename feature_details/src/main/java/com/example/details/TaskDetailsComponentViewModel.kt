package com.example.details

import androidx.lifecycle.ViewModel
import com.example.details.di.DaggerTaskDetailsComponent
import com.example.details.di.TaskDetailsDepsProvider

internal class TaskDetailsComponentViewModel: ViewModel() {
    val taskDetailsComponent = DaggerTaskDetailsComponent.factory().create(TaskDetailsDepsProvider.deps)
}