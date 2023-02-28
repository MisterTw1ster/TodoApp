package com.example.todoapp.exception

import com.example.todoapp.di.DomainScope
import javax.inject.Inject

@DomainScope
class HandleRequest  @Inject constructor(
    private val handleError: HandleError<String>
)  {

    suspend fun handle(block: suspend () -> Unit) = try {
        block.invoke()
    } catch (e: Exception) {
        handleError.handle(e)
    }
}