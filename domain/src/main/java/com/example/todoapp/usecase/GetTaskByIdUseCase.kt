package com.example.todoapp.usecase

import com.example.todoapp.repository.TasksRepository

class GetTaskByIdUseCase(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(id: Long) = repository.getTaskById(id)
}