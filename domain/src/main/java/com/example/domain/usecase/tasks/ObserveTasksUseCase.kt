package com.example.domain.usecase.tasks

import com.example.domain.models.TaskDomain
import com.example.domain.repository.SettingsRepository
import com.example.domain.repository.TasksRepository
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@Reusable
class ObserveTasksUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(userId: String): Flow<List<TaskDomain>> {
        val tasksDomain = tasksRepository.initAndObserveTasks(userId)
        val hideCompleted = settingsRepository.observeHideCompletedFilters()
        val sortMode = settingsRepository.observeSortingTasks()
        return combine(tasksDomain, hideCompleted, sortMode) {tasks, hideCompletedFilter, modeSort ->
            val tasksWithFilters = tasks.takeIf { !hideCompletedFilter }?.filter { !it.isDone } ?: tasks
            when (modeSort) {
                "created_at_desc" -> tasksWithFilters.sortedByDescending { it.createdAt }
                "created_at_inc" -> tasksWithFilters.sortedBy { it.createdAt }
                "change_at_desc" -> tasksWithFilters.sortedByDescending { it.changedAt }
                "change_at_inc" -> tasksWithFilters.sortedBy { it.changedAt }
                else -> tasksWithFilters.sortedByDescending { it.createdAt }
            }
        }
    }
}