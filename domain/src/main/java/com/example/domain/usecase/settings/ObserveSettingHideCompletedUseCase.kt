package com.example.domain.usecase.settings

import com.example.domain.repository.SettingsRepository
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class ObserveSettingHideCompletedUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): Flow<Boolean> = settingsRepository.observeHideCompletedFilters()
}