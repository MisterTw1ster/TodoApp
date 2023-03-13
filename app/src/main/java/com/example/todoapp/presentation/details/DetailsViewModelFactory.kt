package com.example.todoapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.usecase.tasks.AddTaskUseCase
import com.example.todoapp.usecase.tasks.DeleteTaskByIdUseCase
import com.example.todoapp.usecase.tasks.EditTaskUseCase
import com.example.todoapp.usecase.tasks.GetTaskByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DetailsViewModelFactory @AssistedInject constructor(
    @Assisted("taskId") private val taskId: Long,
    @Assisted("communication") private val communicationDetails: CommunicationDetails,
    private val addTaskUseCase: AddTaskUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val getTaskById: GetTaskByIdUseCase,
    private val deleteTaskById: DeleteTaskByIdUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(
            taskId,
            communicationDetails,
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
            @Assisted("communication") communicationDetails: CommunicationDetails
        ): DetailsViewModelFactory
    }

}