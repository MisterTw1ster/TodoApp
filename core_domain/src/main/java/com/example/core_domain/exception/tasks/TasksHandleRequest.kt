package com.example.core_domain.exception.tasks

import com.example.core_domain.exception.HandleError
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