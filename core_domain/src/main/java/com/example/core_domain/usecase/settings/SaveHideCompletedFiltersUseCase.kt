package com.example.core_domain.usecase.settings

import com.example.core_domain.repository.SettingsRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SaveHideCompletedFiltersUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(onlyNotDone: Boolean) = settingsRepository.saveHideCompletedFilters(onlyNotDone)
}