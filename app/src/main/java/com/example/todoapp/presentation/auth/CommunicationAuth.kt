package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.models.UserDomainParams
import com.example.todoapp.presentation.auth.models.UserUI
import com.example.todoapp.presentation.common.navigation.NavigationGraph

interface CommunicationAuth {

    fun getUserDomainParams(): UserDomainParams

    fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserUI>>)
    fun observeError(owner: LifecycleOwner, observer: Observer<String>)
    fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationGraph>)

    fun mapLogin(source: String)
    fun mapPassword(source: String)
    fun mapUsers(source: List<UserUI>)
    fun mapError(source: String)
    fun mapNavigation(source: NavigationGraph)

    class Base (
        private val login: MutableLiveData<String> = MutableLiveData(),
        private val password: MutableLiveData<String> = MutableLiveData(),
        private val users: MutableLiveData<List<UserUI>> = MutableLiveData(),
        private val error: MutableLiveData<String> = MutableLiveData(),
        private val navigation: MutableLiveData<NavigationGraph> = MutableLiveData()
    ): CommunicationAuth {

        override fun getUserDomainParams(): UserDomainParams {
            return UserDomainParams(
                email = login.value!!,//TODO
                password = password.value!! //TODO
            )
        }

        override fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserUI>>) {
            users.observe(owner, observer)
        }

        override fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
            error.observe(owner, observer)
        }

        override fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationGraph>) {
            navigation.observe(owner, observer)
        }

        override fun mapLogin(source: String) {
            login.postValue(source)
        }

        override fun mapPassword(source: String) {
            password.postValue(source)
        }

        override fun mapUsers(source: List<UserUI>) {
            users.postValue(source)
        }

        override fun mapError(source: String) {
            error.postValue(source)
        }

        override fun mapNavigation(source: NavigationGraph) {
            navigation.postValue(source)
        }

    }
}

