package com.example.todoapp.usecase.settings

import com.example.todoapp.di.DomainScope
import com.example.todoapp.repository.SettingsRepository
import javax.inject.Inject

@DomainScope
class SaveSettingHideCompletedUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(onlyNotDone: Boolean) = settingsRepository.saveSettingHideCompleted(onlyNotDone)
}