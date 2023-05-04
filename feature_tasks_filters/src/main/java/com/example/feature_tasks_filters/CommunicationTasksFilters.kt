package com.example.feature_tasks_filters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.core_domain.models.FiltersDomain

class CommunicationTasksFilters constructor(
    private val hideCompleted: MutableLiveData<Boolean> = MutableLiveData(),
    private val closeScreen: MutableLiveData<Boolean> = MutableLiveData(),
) {


    fun getSaveParams(): FiltersDomain {
        return FiltersDomain(hideCompleted = hideCompleted.value!!)
    }

    fun getHideCompleted(): Boolean {
        return hideCompleted.value!!
    }

    fun observeHideCompleted(owner: LifecycleOwner, observer: Observer<Boolean>) {
        hideCompleted.observe(owner, observer)
    }

    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
        closeScreen.observe(owner, observer)
    }

    fun mapHideCompleted(source: Boolean) {
        hideCompleted.postValue(source)
    }

    fun mapCloseScreen(source: Boolean) {
        closeScreen.postValue(source)
    }

}

