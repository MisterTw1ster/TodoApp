package com.example.todoapp.exception

import com.example.todoapp.models.TaskDomain

class HandleDataRequest(
    private val handleError: HandleError<Exception>
) {
    suspend fun handle(block: suspend () -> TaskDomain) = try {
        block.invoke()
    } catch (e: Exception) {
        throw handleError.handle(e)
    }
}