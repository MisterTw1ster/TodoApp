package com.example.todoapp.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.presentation.auth.mappers.UserDomainToUIMapper
import com.example.todoapp.presentation.tasks.mappers.TaskDomainToUIMapper
import com.example.todoapp.presentation.tasks.mappers.TaskUIToDomainParamsMapper
import com.example.todoapp.usecase.*
import com.example.todoapp.usecase.auth.GetCurrentUserUseCase
import com.example.todoapp.usecase.auth.SignOutUseCase
import com.example.todoapp.usecase.settings.ObserveSettingHideCompletedUseCase
import com.example.todoapp.usecase.settings.SaveSettingHideCompletedUseCase
import com.example.todoapp.usecase.tasks.EditTaskUseCase
import com.example.todoapp.usecase.tasks.ObserveCompletedTasksUseCase
import com.example.todoapp.usecase.tasks.ObserveTasksUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TasksViewModelFactory @AssistedInject constructor(
    @Assisted("userId") private val userId: String,
    @Assisted("communication") private val communication: CommunicationTasks,
    private val domainToUIMapper: TaskDomainToUIMapper,
    private val uiToDomainParamsMapper: TaskUIToDomainParamsMapper,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val observeCompletedTasksUseCase: ObserveCompletedTasksUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val observeSettingHideCompletedUseCase: ObserveSettingHideCompletedUseCase,
    private val saveSettingHideCompletedUseCase: SaveSettingHideCompletedUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val userDomainToUIMapper: UserDomainToUIMapper
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(
            userId,
            communication,
            domainToUIMapper,
            uiToDomainParamsMapper,
            observeTasksUseCase,
            observeCompletedTasksUseCase,
            editTaskUseCase,
            observeSettingHideCompletedUseCase,
            saveSettingHideCompletedUseCase,
            getCurrentUserUseCase,
            signOutUseCase,
            userDomainToUIMapper
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("userId") userId: String,
            @Assisted("communication") communication: CommunicationTasks
        ): TasksViewModelFactory
    }

}