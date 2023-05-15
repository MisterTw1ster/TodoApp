package com.example.feature_dialogs.taskImportance.viewModel

import androidx.lifecycle.ViewModel
import com.example.feature_dialogs.taskImportance.di.DaggerTaskImportanceComponent
import com.example.feature_dialogs.taskImportance.di.TaskImportanceDepsProvider

internal class TaskImportanceComponentViewModel: ViewModel() {
    val component = DaggerTaskImportanceComponent.factory().create(TaskImportanceDepsProvider.deps)
}