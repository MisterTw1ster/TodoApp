package com.example.task_list.mappers

import com.example.domain.models.SearchItemDomain
import com.example.task_list.rvSearchSuggestions.SuggestionsItem
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SearchItemDomainToUiMapper @Inject constructor(){
    fun transform(item: SearchItemDomain): SuggestionsItem {
        return SuggestionsItem(
            id = item.id
        )
    }
}