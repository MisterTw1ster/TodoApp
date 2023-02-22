package com.example.todoapp.exception

interface HandleError<T> {

    fun handle(e: Exception): T

    class HandleDomainError() : HandleError<String> {

        override fun handle(e: Exception) = when (e) {
                is NoInternetConnectionException -> "No internet connection"
                else -> "Service is unavailable"
            }

    }
}