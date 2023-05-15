package com.example.domain.exception.users

import com.example.domain.exception.HandleError
import com.example.domain.models.UserDomain
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