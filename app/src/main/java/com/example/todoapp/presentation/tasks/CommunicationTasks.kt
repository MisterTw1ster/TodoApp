package com.example.todoapp.presentation.tasks

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.presentation.tasks.models.StateSettingHideCompletedUI
import com.example.todoapp.presentation.tasks.models.StateTasksUI
import com.example.todoapp.presentation.tasks.models.StateTitleUI

interface CommunicationTasks {

    fun observeTasks(owner: LifecycleOwner, observer: Observer<StateTasksUI>)
//    fun observeStatus(owner: LifecycleOwner, observer: Observer<StateErrorUI>)
    fun observeFilterCompleted(owner: LifecycleOwner, observer: Observer<StateSettingHideCompletedUI>)
    fun observeTitle(owner: LifecycleOwner, observer: Observer<StateTitleUI>)

    fun mapTasks(source: StateTasksUI)
//    fun mapStatus(source: StateErrorUI)
    fun mapFilterCompleted(source: StateSettingHideCompletedUI)
    fun mapTitle(source: StateTitleUI)

    class Base (
        private val tasks: MutableLiveData<StateTasksUI> = MutableLiveData(),
//        private val status: MutableLiveData<StateErrorUI> = MutableLiveData(StateErrorUI.Hide),
        private val filterCompleted: MutableLiveData<StateSettingHideCompletedUI> = MutableLiveData(),
        private val title: MutableLiveData<StateTitleUI> = MutableLiveData()
    ): CommunicationTasks {

        override fun observeTasks(owner: LifecycleOwner, observer: Observer<StateTasksUI>) {
            tasks.observe(owner, observer)
        }

//        override fun observeStatus(owner: LifecycleOwner, observer: Observer<StateErrorUI>) {
//            status.observe(owner, observer)
//        }
//
        override fun observeFilterCompleted(
            owner: LifecycleOwner,
            observer: Observer<StateSettingHideCompletedUI>
        ) {
            filterCompleted.observe(owner, observer)
        }

        override fun observeTitle(owner: LifecycleOwner, observer: Observer<StateTitleUI>) {
            title.observe(owner, observer)
        }

        override fun mapTasks(source: StateTasksUI) {
            tasks.postValue(source)
        }

//        override fun mapStatus(source: StateErrorUI) {
//            status.postValue(source)
//        }
//
        override fun mapFilterCompleted(source: StateSettingHideCompletedUI) {
            filterCompleted.postValue(source)
        }
//
        override fun mapTitle(source: StateTitleUI) {
            title.postValue(source)
        }
    }
}

