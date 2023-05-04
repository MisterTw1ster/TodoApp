package com.example.feature_list.mappers

import com.example.core_domain.models.SearchItemDomain
import com.example.feature_list.rvsearchsuggestions.SuggestionsItem
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