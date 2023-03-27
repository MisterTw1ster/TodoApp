package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.models.UserDomainParams

interface AuthCommunication {

    fun getUserDomainParams(): UserDomainParams

    fun observeErrorLogin(owner: LifecycleOwner, observer: Observer<String>)
    fun observeErrorPassword(owner: LifecycleOwner, observer: Observer<String>)
    fun observeError(owner: LifecycleOwner, observer: Observer<String>)
    fun mapLogin(source: String)
    fun mapPassword(source: String)
    fun mapErrorLogin(source: String)
    fun mapErrorPassword(source: String)
    fun mapError(source: String)

    class Base (
        private val login: MutableLiveData<String> = MutableLiveData(""),
        private val password: MutableLiveData<String> = MutableLiveData(""),
        private val errorLogin: MutableLiveData<String> = MutableLiveData(),
        private val errorPassword: MutableLiveData<String> = MutableLiveData(),
        private val error: MutableLiveData<String> = MutableLiveData(),
    ): AuthCommunication {

        override fun getUserDomainParams(): UserDomainParams {
            return UserDomainParams(
                email = login.value!!,
                password = password.value!!
            )
        }

        override fun observeErrorLogin(owner: LifecycleOwner, observer: Observer<String>) {
            errorLogin.observe(owner, observer)
        }

        override fun observeErrorPassword(owner: LifecycleOwner, observer: Observer<String>) {
            errorPassword.observe(owner, observer)
        }

        override fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
            error.observe(owner, observer)
        }

        override fun mapLogin(source: String) {
            login.postValue(source)
        }

        override fun mapPassword(source: String) {
            password.postValue(source)
        }

        override fun mapErrorLogin(source: String) {
            errorLogin.postValue(source)
        }

        override fun mapErrorPassword(source: String) {
            errorPassword.postValue(source)
        }

        override fun mapError(source: String) {
            error.postValue(source)
        }

    }
}

