package com.example.feature_dialogs.taskSorting.di

import com.example.domain.usecase.settings.GetTasksSortingUseCase
import com.example.domain.usecase.settings.SaveTasksSortingUseCase

interface TaskSortingDeps {
    val getTasksSortingUseCase: GetTasksSortingUseCase
    val saveTasksSortingUseCase: SaveTasksSortingUseCase
}