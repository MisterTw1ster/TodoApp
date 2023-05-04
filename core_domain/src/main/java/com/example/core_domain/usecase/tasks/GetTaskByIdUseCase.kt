package com.example.core_domain.usecase.tasks

import com.example.core_domain.repository.TasksRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetTaskByIdUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(id: Long) = repository.getTaskById(id)
}