package com.example.todoapp.usecase

import com.example.todoapp.models.TasksDomain
import com.example.todoapp.repository.TasksRepository
import kotlinx.coroutines.flow.map

class ObserveTasksUseCase(
    private val repository: TasksRepository
) {
    suspend operator fun invoke() = repository.observeTasks().map { tasks ->
        if (tasks.isEmpty()) TasksDomain.Empty else TasksDomain.Success(tasks)
    }
}