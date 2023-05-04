package com.example.core_users_repository.exception

import com.example.core_domain.exception.HandleError
import com.example.core_domain.models.UserDomain
import com.example.core.di.qualifier.Users
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UsersHandleDataRequest @Inject constructor(
    @Users private val handleError: HandleError<Exception>
) {
    suspend fun handle(block: suspend () -> UserDomain): UserDomain = try {
        block.invoke()
    } catch (e: Exception) {
        throw handleError.handle(e)
    }
}