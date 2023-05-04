package com.example.feature_tasks_filters.di

import com.example.core.ManageResources
import com.example.core_domain.usecase.settings.FetchFiltersUseCase
import com.example.core_domain.usecase.settings.SaveHideCompletedFiltersUseCase

interface TasksFiltersDeps {
    val manageResources: ManageResources
    val fetchTasksFiltersUseCase: FetchFiltersUseCase
    val saveHideCompletedFiltersUseCase: SaveHideCompletedFiltersUseCase
}