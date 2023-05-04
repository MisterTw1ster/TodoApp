package com.example.feature_user_select

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.feature_user_select.rvusers.UserSelectUI

class CommunicationUserSelect(
    private val users: MutableLiveData<List<UserSelectUI>> = MutableLiveData(),
) {

    fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserSelectUI>>) {
        users.observe(owner, observer)
    }

    fun mapUsers(source: List<UserSelectUI>) {
        users.postValue(source)
    }

}

