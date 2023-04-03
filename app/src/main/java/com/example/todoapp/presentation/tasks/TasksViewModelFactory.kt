package com.example.todoapp.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.presentation.auth.mappers.UserDomainToUIMapper
import com.example.todoapp.presentation.common.navigation.newnav.NavigationCommunication
import com.example.todoapp.presentation.tasks.mappers.TaskDomainToUIMapper
import com.example.todoapp.presentation.tasks.mappers.TaskUIToDomainParamsMapper
import com.example.todoapp.usecase.*
import com.example.todoapp.usecase.auth.GetCurrentUserUseCase
import com.example.todoapp.usecase.auth.SignOutUseCase
import com.example.todoapp.usecase.tasks.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TasksViewModelFactory @AssistedInject constructor(
    @Assisted("userId") private val userId: String,
    @Assisted("communication") private val communication: CommunicationTasks,
    @Assisted("communication") private val navigationCommunication: NavigationCommunication.Base,
    private val domainToUIMapper: TaskDomainToUIMapper,
    private val uiToDomainParamsMapper: TaskUIToDomainParamsMapper,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val observeCountCompletedTasksUseCase: ObserveCountCompletedTasksUseCase,
    private val observeCountNotCompletedTasksImportantUseCase: ObserveCountNotCompletedTasksImportantUseCase,
    private val observeCountNotCompletedTasksUseCase: ObserveCountNotCompletedTasksUseCase,
    private val editTaskUseCase: EditTaskUseCase,
//    private val observeSettingHideCompletedUseCase: ObserveSettingHideCompletedUseCase,
//    private val saveSettingHideCompletedUseCase: SaveSettingHideCompletedUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val userDomainToUIMapper: UserDomainToUIMapper
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TasksViewModel(
            userId,
            communication,
            navigationCommunication,
            domainToUIMapper,
            uiToDomainParamsMapper,
            observeTasksUseCase,
            observeCountCompletedTasksUseCase,
            observeCountNotCompletedTasksImportantUseCase,
            observeCountNotCompletedTasksUseCase,
            editTaskUseCase,
//            observeSettingHideCompletedUseCase,
//            saveSettingHideCompletedUseCase,
            getCurrentUserUseCase,
            signOutUseCase,
            userDomainToUIMapper
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("userId") userId: String,
            @Assisted("communication") communication: CommunicationTasks,
            @Assisted("communication") navigationCommunication: NavigationCommunication.Base,
        ): TasksViewModelFactory
    }

}