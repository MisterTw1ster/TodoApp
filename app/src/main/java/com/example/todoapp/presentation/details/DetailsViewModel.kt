package com.example.todoapp.presentation.details

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.presentation.details.models.StateDeadlineUI
import com.example.todoapp.usecase.tasks.AddTaskUseCase
import com.example.todoapp.usecase.tasks.DeleteTaskByIdUseCase
import com.example.todoapp.usecase.tasks.EditTaskUseCase
import com.example.todoapp.usecase.tasks.GetTaskByIdUseCase
import kotlinx.coroutines.*

class DetailsViewModel(
    private val taskId: Long,
    private val userId: String,
    private val communicationDetails: CommunicationDetails,
    private val addTaskUseCase: AddTaskUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val getTaskByIDUseCase: GetTaskByIdUseCase,
    private val deleteTaskById: DeleteTaskByIdUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            var deadlineInit = NEW_TASK_DEADLINE
            if (taskId != NEW_TASK_ID) {
                communicationDetails.mapTaskID(taskId)
                communicationDetails.mapUserID(userId)
                val taskDomain = getTaskByIDUseCase(taskId)
                with(taskDomain) {
                    communicationDetails.mapText(text)
                    communicationDetails.mapImportance(importance)
                    deadlineInit = deadline

                }
            }
            communicationDetails.initDeadline(deadlineInit)
        }
    }


    fun saveTask() {
        scope.launch {
            val taskSaveParam = communicationDetails.getTaskDomainParams()
            if (taskId == NEW_TASK_ID) {
                addTaskUseCase(taskSaveParam)
            } else {
                editTaskUseCase(taskSaveParam)
            }
        }
        closeScreen()
    }

    fun deleteTask() {
        scope.launch {
            deleteTaskById(taskId)
        }
        closeScreen()
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

    fun setTextTask(text: String) {
        communicationDetails.mapText(text)
    }

    fun setImportanceTask(importance: String) {
        communicationDetails.mapImportance(importance)
    }

    fun setDeadlineTask(time: Long = 0L) {
        communicationDetails.mapDeadline(time)
    }

    fun closeScreen() {
        communicationDetails.mapCloseScreen(true)
    }

    companion object {
        private const val NEW_TASK_ID = 0L
        private const val NEW_TASK_DEADLINE = 0L
    }
}

