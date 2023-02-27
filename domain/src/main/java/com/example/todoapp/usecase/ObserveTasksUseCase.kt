package com.example.todoapp.usecase

import com.example.todoapp.repository.TasksRepository

class ObserveTasksUseCase(
    private val repository: TasksRepository
) {
    suspend operator fun invoke() = repository.observeTasks()
}