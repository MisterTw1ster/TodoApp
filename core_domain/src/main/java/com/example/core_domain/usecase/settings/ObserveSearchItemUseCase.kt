package com.example.core_domain.usecase.settings

import com.example.core_domain.models.SearchItemDomain
import com.example.core_domain.repository.SettingsRepository
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