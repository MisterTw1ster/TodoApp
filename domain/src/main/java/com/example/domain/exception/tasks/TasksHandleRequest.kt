package com.example.domain.exception.tasks

import com.example.domain.exception.HandleError
import dagger.Reusable
import javax.inject.Inject

@Reusable
class TasksHandleRequest @Inject constructor (
    private val handleError: HandleError<String>
)  {

    suspend fun handle(block: suspend () -> Unit) = try {
        block.invoke()
    } catch (e: Exception) {
        handleError.handle(e)
    }
}