package com.example.domain.usecase.tasks

import com.example.domain.exception.tasks.TasksHandleRequest
import com.example.domain.models.TaskDomainParams
import com.example.domain.repository.TasksRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class EditTaskUseCase @Inject constructor(
    private val repository: TasksRepository,
    private val tasksHandleRequest: TasksHandleRequest
) {
    suspend operator fun invoke(params: TaskDomainParams) = tasksHandleRequest.handle {
        repository.editTask(params)
    }
}