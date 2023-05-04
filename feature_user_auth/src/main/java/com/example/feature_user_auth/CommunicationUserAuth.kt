package com.example.feature_user_auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.core_domain.models.UserDomainParams

class CommunicationUserAuth(
    private val login: MutableLiveData<String> = MutableLiveData(""),
    private val password: MutableLiveData<String> = MutableLiveData(""),
    private val errorLogin: MutableLiveData<String> = MutableLiveData(),
    private val errorPassword: MutableLiveData<String> = MutableLiveData(),
    private val error: MutableLiveData<String> = MutableLiveData(),
) {

    fun getUserDomainParams(): UserDomainParams {
        return UserDomainParams(
            email = login.value!!,
            password = password.value!!
        )
    }

    fun observeErrorLogin(owner: LifecycleOwner, observer: Observer<String>) {
        errorLogin.observe(owner, observer)
    }

    fun observeErrorPassword(owner: LifecycleOwner, observer: Observer<String>) {
        errorPassword.observe(owner, observer)
    }

    fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
        error.observe(owner, observer)
    }

    fun mapLogin(source: String) {
        login.postValue(source)
    }

    fun mapPassword(source: String) {
        password.postValue(source)
    }

    fun mapErrorLogin(source: String) {
        errorLogin.postValue(source)
    }

     fun mapErrorPassword(source: String) {
        errorPassword.postValue(source)
    }

    fun mapError(source: String) {
        error.postValue(source)
    }

}


