package com.example.todoapp.exception

import java.net.UnknownHostException
import javax.inject.Inject

class HandleDomainError  @Inject constructor(): HandleError<Exception> {

    override fun handle(e: Exception) = when (e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}