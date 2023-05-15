package com.example.user_select.viewModel

import androidx.lifecycle.ViewModel
import com.example.user_select.di.DaggerUserSelectComponent
import com.example.user_select.di.UserSelectDepsProvider

internal class UserSelectComponentViewModel: ViewModel() {
    val userSelectComponent = DaggerUserSelectComponent.factory().create(UserSelectDepsProvider.deps)
}