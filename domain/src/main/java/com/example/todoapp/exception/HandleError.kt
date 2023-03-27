package com.example.todoapp.exception

import com.example.todoapp.di.DomainScope
import com.example.todoapp.exception.auth.EmailNotFoundException
import com.example.todoapp.exception.auth.InvalidEmailException
import com.example.todoapp.exception.auth.InvalidPasswordException
import com.example.todoapp.exception.auth.WeakPasswordException
import javax.inject.Inject

@DomainScope
interface HandleError<T> {

    fun handle(e: Exception): T

    class HandleDomainError @Inject constructor() : HandleError<String> {

        override fun handle(e: Exception) = when (e) {
                is NoInternetConnectionException -> "No internet connection"
                else -> "Service is unavailable"
            }

    }


}
