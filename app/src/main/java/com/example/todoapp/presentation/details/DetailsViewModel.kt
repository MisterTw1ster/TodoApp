package com.example.todoapp.presentation.details

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.presentation.details.models.StateDeadlineUI
import com.example.todoapp.usecase.AddTaskUseCase
import com.example.todoapp.usecase.DeleteTaskByIdUseCase
import com.example.todoapp.usecase.EditTaskUseCase
import com.example.todoapp.usecase.GetTaskByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val taskId: Long,
    private val communicationDetails: CommunicationDetails,
    private val addTaskUseCase: AddTaskUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val getTaskByIDUseCase: GetTaskByIdUseCase,
    private val deleteTaskById: DeleteTaskByIdUseCase
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            var deadlineInit = NEW_TASK_DEADLINE
            if (taskId != NEW_TASK_ID) {
                val taskDomain = getTaskByIDUseCase(taskId)
                with(taskDomain) {
                    communicationDetails.mapTaskID(id)
                    communicationDetails.mapText(text)
                    communicationDetails.mapImportance(importance)
                    deadlineInit = deadline

                }
            }
            communicationDetails.initDeadline(deadlineInit)
        }
    }


    fun observeImportance(owner: LifecycleOwner, observer: Observer<String>) {
        communicationDetails.observeImportance(owner, observer)
    }

    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationDetails.observeCloseScreen(owner, observer)
    }

    fun observeDeadlineState(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>) {
        communicationDetails.observeDeadlineState(owner, observer)
    }

    fun observeText(owner: LifecycleOwner, observer: Observer<String>) {
        communicationDetails.observeText(owner, observer)
    }

    fun saveTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val taskSaveParam = communicationDetails.getTaskDomainParams()
            if (taskId == NEW_TASK_ID) {
                addTaskUseCase(taskSaveParam)
            } else {
                editTaskUseCase(taskSaveParam)
            }
            closeScreen()
        }
    }

    fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskById(taskId)
            closeScreen()
        }
    }

    @MainThread
    fun setTextTask(text: String) {
        communicationDetails.mapText(text)
    }

    @MainThread
    fun setImportanceTask(importance: String) {
        communicationDetails.mapImportance(importance)
    }

    @MainThread
    fun setDeadlineTask(time: Long = 0L) {
        communicationDetails.mapDeadline(time)
    }

    @MainThread
    fun closeScreen() {
        communicationDetails.mapCloseScreen(true)
    }

    companion object {
        private const val NEW_TASK_ID = 0L
        private const val NEW_TASK_DEADLINE = 0L
    }
}

