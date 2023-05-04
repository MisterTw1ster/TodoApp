package com.example.feature_user_select

import androidx.lifecycle.ViewModel
import com.example.feature_user_select.di.DaggerUserSelectComponent
import com.example.feature_user_select.di.UserSelectDepsProvider

internal class UserSelectComponentViewModel: ViewModel() {
    val userSelectComponent = DaggerUserSelectComponent.factory().create(UserSelectDepsProvider.deps)
}