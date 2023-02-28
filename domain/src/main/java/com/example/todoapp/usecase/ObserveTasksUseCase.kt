package com.example.todoapp.usecase

import com.example.todoapp.di.DomainScope
import com.example.todoapp.repository.TasksRepository
import javax.inject.Inject

@DomainScope
class ObserveTasksUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke() = repository.observeTasks()
}