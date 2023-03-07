package com.example.todoapp.exception

import com.example.todoapp.models.TaskDomain
import javax.inject.Inject

class HandleDataRequestNew @Inject constructor(
    private val handleError: HandleError<Exception>
) {
    suspend fun handle(
        blockSuccess: suspend () -> TaskDomain,
        blockFailure: suspend () -> Unit
    ): TaskDomain = try {
        blockSuccess.invoke()
    } catch (e: Exception) {
        blockFailure.invoke()
        throw handleError.handle(e)
    }
}