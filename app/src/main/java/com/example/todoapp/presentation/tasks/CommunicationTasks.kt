package com.example.todoapp.presentation.tasks

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.presentation.auth.models.UserUI
import com.example.todoapp.presentation.tasks.models.StateSettingHideCompletedUI
import com.example.todoapp.presentation.tasks.models.StateTasksUI

interface CommunicationTasks {

    fun observeUser(owner: LifecycleOwner, observer: Observer<UserUI>)
    fun observeTasks(owner: LifecycleOwner, observer: Observer<StateTasksUI>)
    fun observeFilterCompleted(owner: LifecycleOwner, observer: Observer<StateSettingHideCompletedUI>)
    fun observeCntTasksImportantNotCompleted(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeCntTasksNotCompleted(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeCntTasksCompleted(owner: LifecycleOwner, observer: Observer<Int>)

    fun mapUser(source: UserUI)
    fun mapTasks(source: StateTasksUI)
    fun mapFilterCompleted(source: StateSettingHideCompletedUI)
    fun mapCntTasksImportantNotCompleted(source: Int)
    fun mapCntTasksNotCompleted(source: Int)
    fun mapCntTasksCompleted(source: Int)

    class Base (
        private val user: MutableLiveData<UserUI> = MutableLiveData(),
        private val tasks: MutableLiveData<StateTasksUI> = MutableLiveData(),
        private val filterCompleted: MutableLiveData<StateSettingHideCompletedUI> = MutableLiveData(),
        private val cntTasksImportantNotCompleted: MutableLiveData<Int> = MutableLiveData(),
        private val cntTasksNotCompleted: MutableLiveData<Int> = MutableLiveData(),
        private val cntTasksCompleted: MutableLiveData<Int> = MutableLiveData(),
    ): CommunicationTasks {
        override fun observeUser(owner: LifecycleOwner, observer: Observer<UserUI>) {
            user.observe(owner, observer)
        }

        override fun observeTasks(owner: LifecycleOwner, observer: Observer<StateTasksUI>) {
            tasks.observe(owner, observer)
        }

        override fun observeFilterCompleted(
            owner: LifecycleOwner,
            observer: Observer<StateSettingHideCompletedUI>
        ) {
            filterCompleted.observe(owner, observer)
        }

        override fun observeCntTasksImportantNotCompleted(
            owner: LifecycleOwner,
            observer: Observer<Int>
        ) {
            cntTasksImportantNotCompleted.observe(owner, observer)
        }

        override fun observeCntTasksNotCompleted(owner: LifecycleOwner, observer: Observer<Int>) {
            cntTasksNotCompleted.observe(owner, observer)
        }

        override fun observeCntTasksCompleted(owner: LifecycleOwner, observer: Observer<Int>) {
            cntTasksCompleted.observe(owner, observer)
        }

        override fun mapUser(source: UserUI) {
            user.postValue(source)
        }

        override fun mapTasks(source: StateTasksUI) {
            tasks.postValue(source)
        }

        override fun mapFilterCompleted(source: StateSettingHideCompletedUI) {
            filterCompleted.postValue(source)
        }

        override fun mapCntTasksImportantNotCompleted(source: Int) {
            cntTasksImportantNotCompleted.postValue(source)
        }

        override fun mapCntTasksNotCompleted(source: Int) {
            cntTasksNotCompleted.postValue(source)
        }

        override fun mapCntTasksCompleted(source: Int) {
            cntTasksCompleted.postValue(source)
        }

    }
}

