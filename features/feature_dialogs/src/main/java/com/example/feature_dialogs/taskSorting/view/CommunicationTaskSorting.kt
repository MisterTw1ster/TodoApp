package com.example.feature_dialogs.taskSorting.view

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class CommunicationTaskSorting constructor(
    private val sortMode: MutableLiveData<String> = MutableLiveData(),
    private val closeScreen: MutableLiveData<Boolean> = MutableLiveData(),
) {

    fun getSortMode(): String {
        return sortMode.value!!
    }

    fun observeSortMode(owner: LifecycleOwner, observer: Observer<String>) {
        sortMode.observe(owner, observer)
    }

    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
        closeScreen.observe(owner, observer)
    }

    fun mapSortMode(source: String) {
        sortMode.postValue(source)
    }

    fun mapCloseScreen(source: Boolean) {
        closeScreen.postValue(source)
    }

}

