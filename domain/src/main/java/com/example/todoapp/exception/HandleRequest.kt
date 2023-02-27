package com.example.todoapp.exception

import com.example.todoapp.models.TasksDomain

class HandleRequest (
    private val handleError: HandleError<String>
)  {

    suspend fun handle(block: suspend () -> Unit) = try {
        block.invoke()
    } catch (e: Exception) {
        TasksDomain.Failure(handleError.handle(e))
    }
}