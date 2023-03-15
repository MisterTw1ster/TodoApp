package com.example.todoapp.presentation.auth.mappers

import com.example.todoapp.models.UserDomain
import com.example.todoapp.presentation.auth.models.UserUI
import javax.inject.Inject

class UserDomainToUIMapper @Inject constructor() {
    fun transform(user: UserDomain): UserUI {
        return UserUI(
            localId = user.localId,
            email = user.email
        )
    }
}