package com.example.todoapp.usecase.settings

import com.example.todoapp.di.DomainScope
import com.example.todoapp.models.FiltersDomain
import com.example.todoapp.repository.SettingsRepository
import javax.inject.Inject

@DomainScope
class FetchFiltersUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): FiltersDomain {
        val hideCompleted = settingsRepository.getHideCompletedFilters()
        return FiltersDomain(hideCompleted = hideCompleted)
    }
}