package com.example.feature_user_auth

import androidx.lifecycle.ViewModel
import com.example.feature_user_auth.di.DaggerUserAuthComponent
import com.example.feature_user_auth.di.UserAuthDepsProvider

internal class UserAuthComponentViewModel : ViewModel() {
    val userAuthComponent = DaggerUserAuthComponent.factory().create(UserAuthDepsProvider.deps)
}