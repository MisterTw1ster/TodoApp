package com.example.core_domain.exception.users

import com.example.core_domain.exception.HandleError
import com.example.core_domain.exception.tasks.NoInternetConnectionException
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UsersHandleDomainToUsersResultError @Inject constructor() : HandleError<UserResult> {

    override fun handle(e: Exception) = when (e) {
        is NoInternetConnectionException -> UserResult.Failure("No internet connection")
        is EmailNotFoundException -> UserResult.LoginError("Email not found")
        is InvalidEmailException -> UserResult.LoginError("Invalid email")
        is InvalidPasswordException -> UserResult.PasswordError("Invalid password")
        is WeakPasswordException -> UserResult.PasswordError("Weak password")
        else -> UserResult.Failure("Service is unavailable")
    }

}