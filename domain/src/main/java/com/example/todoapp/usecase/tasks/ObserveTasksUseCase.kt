package com.example.todoapp.usecase.tasks

import com.example.todoapp.di.DomainScope
import com.example.todoapp.models.TaskDomain
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
        val hideCompleted = settingsRepository.observeHideCompletedFilters()
        val sortMode = settingsRepository.observeSortingTasks()
        return combine(tasksDomain, hideCompleted, sortMode) {tasks, hideCompletedFilter, sortMode ->
            val tasksWithFilters = tasks.takeIf { !hideCompletedFilter }?.filter { !it.isDone } ?: tasks
            when (sortMode) {
                "created_at_desc" -> tasksWithFilters.sortedByDescending { it.createdAt }
                "created_at_inc" -> tasksWithFilters.sortedBy { it.createdAt }
                "change_at_desc" -> tasksWithFilters.sortedByDescending { it.changedAt }
                "change_at_inc" -> tasksWithFilters.sortedBy { it.changedAt }
                else -> tasksWithFilters.sortedByDescending { it.createdAt }
            }
        }
    }
}