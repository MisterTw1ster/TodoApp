package com.example.todoapp.usecase.tasks

import com.example.todoapp.di.DomainScope
import com.example.todoapp.exception.HandleRequest
import com.example.todoapp.models.TaskDomainParams
import com.example.todoapp.repository.TasksRepository
import javax.inject.Inject

@DomainScope
class EditTaskUseCase @Inject constructor(
    private val repository: TasksRepository,
    private val handleRequest: HandleRequest
) {
    suspend operator fun invoke(params: TaskDomainParams) = handleRequest.handle {
        repository.editTask(params)
    }
}