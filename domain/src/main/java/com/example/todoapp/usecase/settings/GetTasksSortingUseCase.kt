package com.example.todoapp.usecase.settings

import com.example.todoapp.di.DomainScope
import com.example.todoapp.repository.SettingsRepository
import javax.inject.Inject

@DomainScope
class GetTasksSortingUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): String {
        return settingsRepository.getSortingTasks()
    }
}