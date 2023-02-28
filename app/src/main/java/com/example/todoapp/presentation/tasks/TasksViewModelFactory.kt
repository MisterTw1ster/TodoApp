package com.example.todoapp.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.presentation.tasks.mappers.TaskDomainToUIMapper
import com.example.todoapp.usecase.ObserveTasksUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

class TasksViewModelFactory @AssistedInject constructor(
    @Assisted("communication") private val communication: CommunicationTasks,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val domainToUIMapper: TaskDomainToUIMapper
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(
            communication,
            observeTasksUseCase,
            domainToUIMapper
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("communication") communication: CommunicationTasks
        ): TasksViewModelFactory
    }

}