package com.example.todoapp.exception

import javax.inject.Inject

interface HandleError<T> {

    fun handle(e: Exception): T

    class HandleDomainError @Inject constructor() : HandleError<String> {

        override fun handle(e: Exception) = when (e) {
                is NoInternetConnectionException -> "No internet connection"
                else -> "Service is unavailable"
            }

    }
}