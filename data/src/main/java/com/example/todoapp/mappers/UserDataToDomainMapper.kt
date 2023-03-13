package com.example.todoapp.mappers

import com.example.todoapp.models.UserData
import com.example.todoapp.models.UserDomain
import javax.inject.Inject

class UserDataToDomainMapper @Inject constructor() {
    fun transform(user: UserData): UserDomain {
        return UserDomain(
            localId = user.localId,
            email = user.email
        )
    }
}