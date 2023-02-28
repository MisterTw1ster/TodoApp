package com.example.todoapp.exception

import javax.inject.Inject

class HandleRequest  @Inject constructor(
    private val handleError: HandleError<String>
)  {

    suspend fun handle(block: suspend () -> Unit) = try {
        block.invoke()
    } catch (e: Exception) {
        handleError.handle(e)
    }
}