package com.example.domain.usecase.settings

import com.example.domain.repository.SettingsRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SaveTasksSortingUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(sortMode: String) = settingsRepository.saveSortingTasks(sortMode)
}