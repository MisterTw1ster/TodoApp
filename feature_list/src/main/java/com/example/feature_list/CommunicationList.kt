package com.example.feature_list

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.feature_list.models.StateTasksUI
import com.example.feature_list.rvsearchsuggestions.SuggestionsItem
import kotlinx.coroutines.flow.MutableStateFlow

class CommunicationList(
    private val tasks: MutableLiveData<StateTasksUI> = MutableLiveData(),
    private val listSuggestions: MutableLiveData<List<SuggestionsItem>> = MutableLiveData(),
    private val userEmail: MutableLiveData<String> = MutableLiveData(),
    private val searchText: MutableStateFlow<String?> = MutableStateFlow(null)
) {

    fun getSuggestionsItem(text: String): SuggestionsItem {
        return SuggestionsItem(id = text)
    }

    fun observeTasks(owner: LifecycleOwner, observer: Observer<StateTasksUI>) {
        tasks.observe(owner, observer)
    }

    fun observeListSuggestions(
        owner: LifecycleOwner,
        observer: Observer<List<SuggestionsItem>>
    ) {
        listSuggestions.observe(owner, observer)
    }

    fun observeUserEmail(owner: LifecycleOwner, observer: Observer<String>) {
        userEmail.observe(owner, observer)
    }

    fun observeSearchText(): MutableStateFlow<String?> {
        return searchText
    }

    fun mapTasks(source: StateTasksUI) {
        tasks.postValue(source)
    }

    fun mapListSuggestions(source: List<SuggestionsItem>) {
        listSuggestions.postValue(source)
    }

    fun mapUserEmail(source: String) {
        userEmail.postValue(source)
    }

    fun mapSearchText(source: String?) {
        searchText.value = source
    }
}


