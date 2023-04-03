package com.example.todoapp.presentation.sorting

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface CommunicationSorting {

    fun getSortMode(): String

    fun observeSortMode(owner: LifecycleOwner, observer: Observer<String>)
    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>)

    fun mapSortMode(source: String)
    fun mapCloseScreen(source: Boolean)

    class BaseSorting constructor(
        private val sortMode: MutableLiveData<String> = MutableLiveData(),
        private val closeScreen: MutableLiveData<Boolean> = MutableLiveData(),
    ) : CommunicationSorting {


        override fun getSortMode(): String {
            return sortMode.value!!
        }

        override fun observeSortMode(owner: LifecycleOwner, observer: Observer<String>) {
            sortMode.observe(owner, observer)
        }

        override fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
            closeScreen.observe(owner, observer)
        }

        override fun mapSortMode(source: String) {
            sortMode.postValue(source)
        }

        override fun mapCloseScreen(source: Boolean) {
            closeScreen.postValue(source)
        }

    }
}
