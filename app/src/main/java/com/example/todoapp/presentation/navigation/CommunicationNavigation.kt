package com.example.todoapp.presentation.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.components.presentation.navigation.NavigationStrategy

class CommunicationNavigation(
    private val liveData: MutableLiveData<NavigationStrategy> = MutableLiveData()
) {

    fun map(source: NavigationStrategy) {
        liveData.value = source
    }

    fun observe(owner: LifecycleOwner, observer: Observer<NavigationStrategy>) {
        liveData.observe(owner, observer)
    }

}


