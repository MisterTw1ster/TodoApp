package com.example.task_details.viewModel

import androidx.lifecycle.ViewModel
import com.example.task_details.di.DaggerTaskDetailsComponent
import com.example.task_details.di.TaskDetailsDepsProvider

internal class TaskDetailsComponentViewModel: ViewModel() {
    val component = DaggerTaskDetailsComponent.factory().create(TaskDetailsDepsProvider.deps)
}