package com.example.task_details.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.navigation.Navigation
import com.example.domain.usecase.tasks.AddTaskUseCase
import com.example.domain.usecase.tasks.DeleteTaskUseCase
import com.example.domain.usecase.tasks.EditTaskUseCase
import com.example.domain.usecase.tasks.GetTaskByIdUseCase
import com.example.task_details.view.CommunicationTaskDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TaskDetailsViewModelFactory @AssistedInject constructor(
    @Assisted("taskId") private val taskId: Long,
    @Assisted("userId") private val userId: String,
    private val communicationTaskDetails: CommunicationTaskDetails,
    private val navigation: Navigation,
    private val addTaskUseCase: AddTaskUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val getTaskById: GetTaskByIdUseCase,
    private val deleteTaskById: DeleteTaskUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(
            taskId,
            userId,
            communicationTaskDetails,
            navigation,
            addTaskUseCase,
            editTaskUseCase,
            getTaskById,
            deleteTaskById
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("taskId") taskId: Long,
            @Assisted("userId") userId: String
        ): TaskDetailsViewModelFactory
    }

}