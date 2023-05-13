package com.example.feature_dialogs.taskSorting.di

import com.example.core_domain.usecase.settings.GetTasksSortingUseCase
import com.example.core_domain.usecase.settings.SaveTasksSortingUseCase

interface TaskSortingDeps {
    val getTasksSortingUseCase: GetTasksSortingUseCase
    val saveTasksSortingUseCase: SaveTasksSortingUseCase
}