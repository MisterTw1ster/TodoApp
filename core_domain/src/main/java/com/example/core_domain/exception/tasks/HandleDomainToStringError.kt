package com.example.core_domain.exception.tasks

import com.example.core_domain.exception.HandleError
import dagger.Reusable
import javax.inject.Inject

@Reusable
class HandleDomainToStringError @Inject constructor() : HandleError<String> {

    override fun handle(e: Exception) = when (e) {
        is NoInternetConnectionException -> "No internet connection"
        else -> "Service is unavailable"
    }
}