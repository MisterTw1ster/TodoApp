package com.example.todoapp.usecase

import com.example.todoapp.repository.TasksRepository
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(id: Long) = repository.getTaskById(id)
}