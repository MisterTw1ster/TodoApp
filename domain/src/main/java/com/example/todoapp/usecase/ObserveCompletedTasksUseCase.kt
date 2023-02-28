package com.example.todoapp.usecase

import com.example.todoapp.di.DomainScope
import com.example.todoapp.repository.TasksRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@DomainScope
class ObserveCompletedTasksUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke() = repository.observeTasks().map { tasks ->
        tasks.count { task -> task.isDone }
    }
}