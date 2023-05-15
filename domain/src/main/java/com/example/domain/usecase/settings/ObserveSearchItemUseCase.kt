package com.example.domain.usecase.settings

import com.example.domain.models.SearchItemDomain
import com.example.domain.repository.SettingsRepository
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class ObserveSearchItemUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(section: String): Flow<List<SearchItemDomain>> =
        settingsRepository.observeSearchItem(section)
}