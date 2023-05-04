package com.example.feature_tasks_sorting.di

import com.example.core_domain.usecase.settings.GetTasksSortingUseCase
import com.example.core_domain.usecase.settings.SaveTasksSortingUseCase

interface TasksSortingDeps {
    val getTasksSortingUseCase: GetTasksSortingUseCase
    val saveTasksSortingUseCase: SaveTasksSortingUseCase
}