package com.example.todoapp.exception

import com.example.todoapp.exception.auth.*
import java.net.UnknownHostException
import javax.inject.Inject

class AuthHandleDomainError @Inject constructor(): HandleError<Exception> {

    override fun handle(e: Exception): DomainException = when (e) {
        is IllegalArgumentException -> ChooseIllegalArgumentException(e).map()
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}

class ChooseIllegalArgumentException(
    private val e: Exception
) {
    private val emailNotFound: AuthDomainException by lazy { EmailNotFoundException() }
    private val invalidEmailException: AuthDomainException by lazy { InvalidEmailException() }
    private val invalidPasswordException: AuthDomainException by lazy { InvalidPasswordException() }
    private val weakPasswordException: AuthDomainException by lazy { WeakPasswordException() }
    private val unknown: AuthDomainException by lazy { UnknownException() }
    fun map(): AuthDomainException = when (e.message) {
        "EMAIL_NOT_FOUND" -> emailNotFound
        "INVALID_EMAIL" -> invalidEmailException
        "INVALID_PASSWORD" -> invalidPasswordException
        "WEAK_PASSWORD" -> weakPasswordException
        else -> unknown
    }
}
