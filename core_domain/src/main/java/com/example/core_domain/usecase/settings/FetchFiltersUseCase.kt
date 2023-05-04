package com.example.core_domain.usecase.settings

import com.example.core_domain.models.FiltersDomain
import com.example.core_domain.repository.SettingsRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class FetchFiltersUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): FiltersDomain {
        val hideCompleted = settingsRepository.getHideCompletedFilters()
        return FiltersDomain(hideCompleted = hideCompleted)
    }
}