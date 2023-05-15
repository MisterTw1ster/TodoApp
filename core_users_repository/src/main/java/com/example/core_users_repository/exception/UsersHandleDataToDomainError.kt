package com.example.core_users_repository.exception

import com.example.domain.exception.HandleError
import com.example.domain.exception.users.*
import dagger.Reusable
import java.net.UnknownHostException
import javax.inject.Inject

@Reusable
class UsersHandleDataToDomainError @Inject constructor(): HandleError<Exception> {

    override fun handle(e: Exception): UsersDomainException = when (e) {
        is IllegalArgumentException -> ChooseIllegalArgumentException(e).map()
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}

class ChooseIllegalArgumentException(
    private val e: Exception
) {
    private val emailNotFound: UsersDomainException by lazy { EmailNotFoundException() }
    private val invalidEmailException: UsersDomainException by lazy { InvalidEmailException() }
    private val invalidPasswordException: UsersDomainException by lazy { InvalidPasswordException() }
    private val weakPasswordException: UsersDomainException by lazy { WeakPasswordException() }
    private val unknown: UsersDomainException by lazy { UnknownException() }
    fun map(): UsersDomainException = when (e.message) {
        "EMAIL_NOT_FOUND" -> emailNotFound
        "INVALID_EMAIL" -> invalidEmailException
        "INVALID_PASSWORD" -> invalidPasswordException
        "WEAK_PASSWORD" -> weakPasswordException
        else -> unknown
    }
}
