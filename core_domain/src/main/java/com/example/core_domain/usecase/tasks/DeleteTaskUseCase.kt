package com.example.core_domain.usecase.tasks

import com.example.core_domain.exception.tasks.TasksHandleRequest
import com.example.core_domain.models.TaskDomainParams
import com.example.core_domain.repository.TasksRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class DeleteTaskUseCase @Inject constructor(
    private val repository: TasksRepository,
    private val tasksHandleRequest: TasksHandleRequest
) {
    suspend operator fun invoke(taskDomainParams: TaskDomainParams) = tasksHandleRequest.handle {
        repository.deleteTask(taskDomainParams)
    }
}