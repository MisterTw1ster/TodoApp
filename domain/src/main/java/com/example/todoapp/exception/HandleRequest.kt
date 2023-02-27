package com.example.todoapp.exception

class HandleRequest (
    private val handleError: HandleError<String>
)  {

    suspend fun handle(block: suspend () -> Unit) = try {
        block.invoke()
    } catch (e: Exception) {
        handleError.handle(e)
    }
}