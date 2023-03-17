package com.example.todoapp.presentation.tasks

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.presentation.auth.models.UserUI
import com.example.todoapp.presentation.tasks.models.StateSettingHideCompletedUI
import com.example.todoapp.presentation.tasks.models.StateTasksUI
import com.example.todoapp.presentation.tasks.models.StateTitleUI

interface CommunicationTasks {

    fun observeTasks(owner: LifecycleOwner, observer: Observer<StateTasksUI>)
    fun observeFilterCompleted(owner: LifecycleOwner, observer: Observer<StateSettingHideCompletedUI>)
    fun observeTitle(owner: LifecycleOwner, observer: Observer<StateTitleUI>)
//    fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationGraph>)

    fun mapUser(source: UserUI?)
    fun mapTasks(source: StateTasksUI)
    fun mapFilterCompleted(source: StateSettingHideCompletedUI)
    fun mapTitle(source: StateTitleUI)
//    fun mapNavigation(source: NavigationGraph)

    class Base (
        private val user: MutableLiveData<UserUI?> = MutableLiveData(),
        private val tasks: MutableLiveData<StateTasksUI> = MutableLiveData(),
        private val filterCompleted: MutableLiveData<StateSettingHideCompletedUI> = MutableLiveData(),
        private val title: MutableLiveData<StateTitleUI> = MutableLiveData(),
//        private val navigation: MutableLiveData<NavigationGraph> = MutableLiveData()
    ): CommunicationTasks {

        override fun observeTasks(owner: LifecycleOwner, observer: Observer<StateTasksUI>) {
            tasks.observe(owner, observer)
        }

        override fun observeFilterCompleted(
            owner: LifecycleOwner,
            observer: Observer<StateSettingHideCompletedUI>
        ) {
            filterCompleted.observe(owner, observer)
        }

        override fun observeTitle(owner: LifecycleOwner, observer: Observer<StateTitleUI>) {
            title.observe(owner, observer)
        }

//        override fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationGraph>) {
//            navigation.observe(owner, observer)
//        }

        override fun mapUser(source: UserUI?) {
            user.postValue(source)
        }

        override fun mapTasks(source: StateTasksUI) {
            tasks.postValue(source)
        }

        override fun mapFilterCompleted(source: StateSettingHideCompletedUI) {
            filterCompleted.postValue(source)
        }

        override fun mapTitle(source: StateTitleUI) {
            title.postValue(source)
        }

//        override fun mapNavigation(source: NavigationGraph) {
//            navigation.postValue(source)
//        }
    }
}

