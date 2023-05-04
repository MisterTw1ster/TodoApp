package com.example.feature_user_select.mappers

import com.example.core_domain.models.UserDomain
import com.example.feature_user_select.rvusers.UserSelectUI
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