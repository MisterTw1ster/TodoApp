package com.example.todoapp.exception

import java.net.UnknownHostException

class HandleDomainError: HandleError<Exception> {

    override fun handle(e: Exception) = when (e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}