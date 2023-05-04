package com.example.core_users_repository.mappers

import com.example.core_domain.models.UserDomain
import com.example.core_users_repository.models.UserData
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UserDataToDomainMapper @Inject constructor() {
    fun transform(user: UserData): UserDomain {
        return UserDomain(
            localId = user.localId,
            email = user.email
        )
    }
}