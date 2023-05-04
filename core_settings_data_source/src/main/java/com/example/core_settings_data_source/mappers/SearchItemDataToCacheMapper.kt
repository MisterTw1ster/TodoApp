package com.example.core_settings_data_source.mappers

import com.example.core_settings_data_source.models.SearchItemCache
import com.example.core_settings_repository.models.SearchItemData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SearchItemDataToCacheMapper @Inject constructor(){
    fun transform(item: SearchItemData): SearchItemCache {
        return SearchItemCache(
            id = item.id,
            section = item.section,
            date = System.currentTimeMillis()
        )
    }
}