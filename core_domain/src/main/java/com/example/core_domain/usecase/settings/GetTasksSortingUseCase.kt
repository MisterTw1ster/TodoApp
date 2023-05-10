package com.example.core_domain.usecase.settings

import com.example.core_domain.repository.SettingsRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetTasksSortingUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): String {
        return settingsRepository.getSortingTasks()
    }
}