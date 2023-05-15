package com.example.user_auth.viewModel

import androidx.lifecycle.ViewModel
import com.example.user_auth.di.DaggerUserAuthComponent
import com.example.user_auth.di.UserAuthDepsProvider

internal class UserAuthComponentViewModel : ViewModel() {
    val component = DaggerUserAuthComponent.factory().create(UserAuthDepsProvider.deps)
}