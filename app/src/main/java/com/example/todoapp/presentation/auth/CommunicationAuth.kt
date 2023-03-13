package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.models.UserDomain

interface CommunicationAuth {

    fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserDomain>>)
    fun observeError(owner: LifecycleOwner, observer: Observer<String>)

    fun mapUsers(source: List<UserDomain>)
    fun mapError(source: String)

    class Base (
        private val users: MutableLiveData<List<UserDomain>> = MutableLiveData(),
        private val error: MutableLiveData<String> = MutableLiveData()
    ): CommunicationAuth {

        override fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserDomain>>) {
            users.observe(owner, observer)
        }

        override fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
            error.observe(owner, observer)
        }

        override fun mapUsers(source: List<UserDomain>) {
            users.postValue(source)
        }

        override fun mapError(source: String) {
            error.postValue(source)
        }

    }
}

