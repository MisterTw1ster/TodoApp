package com.example.domain.usecase.tasks

import com.example.domain.repository.TasksRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetTaskByIdUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(id: Long) = repository.getTaskById(id)
}