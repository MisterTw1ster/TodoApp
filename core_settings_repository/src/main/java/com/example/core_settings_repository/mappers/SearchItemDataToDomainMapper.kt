package com.example.core_settings_repository.mappers

import com.example.domain.models.SearchItemDomain
import com.example.core_settings_repository.models.SearchItemData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SearchItemDataToDomainMapper @Inject constructor(){
    fun transform(item: SearchItemData): SearchItemDomain {
        return SearchItemDomain(
            id = item.id,
            section = item.section
        )
    }
}