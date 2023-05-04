package com.example.core_task_repository.exception

import com.example.core_domain.exception.HandleError
import com.example.core_domain.exception.tasks.NoInternetConnectionException
import com.example.core_domain.exception.tasks.ServiceUnavailableException
import dagger.Reusable
import java.net.UnknownHostException
import javax.inject.Inject

@Reusable
class TasksHandleDataToDomainError @Inject constructor() : HandleError<Exception> {

    override fun handle(e: Exception) = when (e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}