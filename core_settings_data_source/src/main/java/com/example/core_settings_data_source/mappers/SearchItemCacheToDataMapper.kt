package com.example.core_settings_data_source.mappers

import com.example.core_settings_data_source.models.SearchItemCache
import com.example.core_settings_repository.models.SearchItemData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SearchItemCacheToDataMapper @Inject constructor(){
    fun transform(item: SearchItemCache): SearchItemData {
        return SearchItemData(
            id = item.id,
            section = item.section
        )
    }
}