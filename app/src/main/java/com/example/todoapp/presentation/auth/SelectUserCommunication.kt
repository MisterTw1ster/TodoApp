package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.presentation.auth.models.UserUI

interface SelectUserCommunication {

//    fun getUserDomainParams(): UserDomainParams

//    fun observeStateScreen(owner: LifecycleOwner, observer: Observer<StateScreenUI>)
    fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserUI>>)
//    fun observeError(owner: LifecycleOwner, observer: Observer<String>)
//    fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationGraph>)

//    fun mapStateScreen(source: StateScreenUI)
//    fun mapLogin(source: String)
//    fun mapPassword(source: String)
    fun mapUsers(source: List<UserUI>)
//    fun mapError(source: String)
//    fun mapNavigation(source: NavigationGraph)

    class Base (
//        private val login: MutableLiveData<String> = MutableLiveData(),
//        private val password: MutableLiveData<String> = MutableLiveData(),
//        private val stateScreen: MutableLiveData<StateScreenUI> = MutableLiveData(),
        private val users: MutableLiveData<List<UserUI>> = MutableLiveData(),
//        private val error: MutableLiveData<String> = MutableLiveData(),
//        private val navigation: MutableLiveData<NavigationGraph> = MutableLiveData()
    ): SelectUserCommunication {

//        override fun getUserDomainParams(): UserDomainParams {
//            return UserDomainParams(
//                email = login.value!!,//TODO
//                password = password.value!! //TODO
//            )
//        }
//
//        override fun observeStateScreen(owner: LifecycleOwner, observer: Observer<StateScreenUI>) {
//            stateScreen.observe(owner, observer)
//        }

        override fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserUI>>) {
            users.observe(owner, observer)
        }

//        override fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
//            error.observe(owner, observer)
//        }

//        override fun observeNavigation(owner: LifecycleOwner, observer: Observer<NavigationGraph>) {
//            navigation.observe(owner, observer)
//        }

//        override fun mapStateScreen(source: StateScreenUI) {
//            stateScreen.postValue(source)
//        }

//        override fun mapLogin(source: String) {
//            login.postValue(source)
//        }

//        override fun mapPassword(source: String) {
//            password.postValue(source)
//        }

        override fun mapUsers(source: List<UserUI>) {
            users.postValue(source)
        }

//        override fun mapError(source: String) {
//            error.postValue(source)
//        }

//        override fun mapNavigation(source: NavigationGraph) {
//            navigation.postValue(source)
//        }

    }
}

