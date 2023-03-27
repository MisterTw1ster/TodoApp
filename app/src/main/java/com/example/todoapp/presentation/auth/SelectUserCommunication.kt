package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.presentation.auth.models.UserUI

interface SelectUserCommunication {

    fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserUI>>)
    fun mapUsers(source: List<UserUI>)

    class Base (
        private val users: MutableLiveData<List<UserUI>> = MutableLiveData(),
    ): SelectUserCommunication {

        override fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserUI>>) {
            users.observe(owner, observer)
        }

        override fun mapUsers(source: List<UserUI>) {
            users.postValue(source)
        }

    }
}

