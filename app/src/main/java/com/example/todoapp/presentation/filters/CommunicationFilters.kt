package com.example.todoapp.presentation.filters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.presentation.filters.models.FilterSaveParamsUI

interface CommunicationFilters {

    fun getSaveParams(): FilterSaveParamsUI
    fun getHideCompleted(): Boolean

    fun observeHideCompleted(owner: LifecycleOwner, observer: Observer<Boolean>)
    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>)

    fun mapHideCompleted(source: Boolean)
    fun mapCloseScreen(source: Boolean)

    class BaseFilters constructor(
        private val hideCompleted: MutableLiveData<Boolean> = MutableLiveData(),
        private val closeScreen: MutableLiveData<Boolean> = MutableLiveData(),
    ) : CommunicationFilters {


        override fun getSaveParams(): FilterSaveParamsUI {
            return FilterSaveParamsUI(hideCompleted = hideCompleted.value!!)
        }

        override fun getHideCompleted(): Boolean {
            return hideCompleted.value!!
        }

        override fun observeHideCompleted(owner: LifecycleOwner, observer: Observer<Boolean>) {
            hideCompleted.observe(owner, observer)
        }

        override fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
            closeScreen.observe(owner, observer)
        }

        override fun mapHideCompleted(source: Boolean) {
            hideCompleted.postValue(source)
        }

        override fun mapCloseScreen(source: Boolean) {
            closeScreen.postValue(source)
        }

    }
}
