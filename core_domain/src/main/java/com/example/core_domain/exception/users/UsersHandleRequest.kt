package com.example.core_domain.exception.users

import com.example.core_domain.exception.HandleError
import com.example.core_domain.models.UserDomain
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UsersHandleRequest @Inject constructor (
    private val handleError: HandleError<UserResult>
)  {

    suspend fun handle(block: suspend () -> UserDomain): UserResult = try {
        val userDomain = block.invoke()
        UserResult.Success(userDomain)
    } catch (e: Exception) {
        handleError.handle(e)
    }
}