package com.example.todoapp.usecase

import com.example.todoapp.repository.TasksRepository
import javax.inject.Inject


class ObserveTasksUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke() = repository.observeTasks()
}