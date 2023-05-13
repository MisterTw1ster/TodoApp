package com.example.feature_dialogs.taskFilter.di

import com.example.core.ManageResources
import com.example.core_domain.usecase.settings.FetchFiltersUseCase
import com.example.core_domain.usecase.settings.SaveHideCompletedFiltersUseCase

interface TaskFilterDeps {
    val manageResources: ManageResources
    val fetchTasksFiltersUseCase: FetchFiltersUseCase
    val saveHideCompletedFiltersUseCase: SaveHideCompletedFiltersUseCase
}