package com.example.feature_list.mappers

import com.example.core_domain.models.SearchItemDomain
import com.example.feature_list.rvsearchsuggestions.SuggestionsItem
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SearchItemUiToDomainMapper @Inject constructor(){
    fun transform(item: SuggestionsItem): SearchItemDomain {
        return SearchItemDomain(
            id = item.id,
            section = SEARCH_SECTION
        )
    }

    companion object {
        private const val SEARCH_SECTION = "tasks"
    }
}