package com.example.core_task_repository.exception

import com.example.core_domain.exception.HandleError
import com.example.core_domain.models.TaskDomain
import com.example.core.di.qualifier.Tasks
import dagger.Reusable
import javax.inject.Inject

@Reusable
class TasksHandleDataRequest @Inject constructor(
    @Tasks private val handleError: HandleError<Exception>
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