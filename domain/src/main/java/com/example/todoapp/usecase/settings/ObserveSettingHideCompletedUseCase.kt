package com.example.todoapp.usecase.settings

import com.example.todoapp.di.DomainScope
import com.example.todoapp.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@DomainScope
class ObserveSettingHideCompletedUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): Flow<Boolean> = settingsRepository.observeHideCompletedFilters()
}