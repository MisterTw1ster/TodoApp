package com.example.task_list.mappers

import com.example.domain.models.SearchItemDomain
import com.example.task_list.rvSearchSuggestions.SuggestionsItem
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