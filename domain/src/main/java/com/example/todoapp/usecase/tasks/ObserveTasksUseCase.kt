package com.example.todoapp.usecase.tasks

import com.example.todoapp.di.DomainScope
import com.example.todoapp.models.TaskDomain
import com.example.todoapp.repository.AuthRepository
import com.example.todoapp.repository.SettingsRepository
import com.example.todoapp.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@DomainScope
class ObserveTasksUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(userId: String): Flow<List<TaskDomain>> {
        val tasksDomain = tasksRepository.observeTasks(userId)
        val hideCompleted = settingsRepository.observeSettingHideCompleted()
        return tasksDomain.combine(hideCompleted) { tasks, filter ->
            tasks.takeIf { !filter }?.filter { !it.isDone } ?: tasks
        }
    }
}