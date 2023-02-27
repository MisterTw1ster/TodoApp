package com.example.todoapp.usecase

import com.example.todoapp.exception.HandleRequest
import com.example.todoapp.models.TaskDomainParams
import com.example.todoapp.repository.TasksRepository

class AddTaskUseCase(
    private val repository: TasksRepository,
    private val handleRequest: HandleRequest
) {
    suspend operator fun invoke(params: TaskDomainParams) = handleRequest.handle {
        repository.addTask(params)
    }
}