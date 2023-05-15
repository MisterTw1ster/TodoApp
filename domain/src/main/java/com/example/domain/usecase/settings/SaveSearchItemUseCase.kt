package com.example.domain.usecase.settings

import com.example.domain.models.SearchItemDomain
import com.example.domain.repository.SettingsRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SaveSearchItemUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(item: SearchItemDomain) = settingsRepository.saveSearchItem(item)
}