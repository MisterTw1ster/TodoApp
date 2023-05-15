package com.example.user_select.mappers

import com.example.domain.models.UserDomain
import com.example.user_select.rvusers.UserSelectUI
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UserDomainToUIMapper @Inject constructor() {
    fun transform(user: UserDomain): UserSelectUI {
        return UserSelectUI(
            localId = user.localId,
            email = user.email
        )
    }
}