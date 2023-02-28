package com.example.todoapp.presentation.details

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.usecase.AddTaskUseCase
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
//    private val deleteTaskById: DeleteTaskByIdUseCase,
//    private val longDateToString: LongDateToString
) : ViewModel() {

    init {

        if (taskId == 0L) {
//            communicationDetails.mapDeadline(
//                StateDeadlineUI.Initial(
//                    false,
//                    StateDeadlineUI.Off()
//                )
//            )
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val taskDomain = getTaskByIDUseCase(taskId)
                communicationDetails.initTask(taskDomain)
            }
        }
    }

//    fun observeStateDeadline(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>) {
//        communicationDetails.observeDeadline(owner, observer)
//    }

    fun observeIsClose(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationDetails.observeIsClose(owner, observer)
    }

    fun observeText(owner: LifecycleOwner, observer: Observer<String>) {
        communicationDetails.observeText(owner, observer)
    }

    fun observeImportance(owner: LifecycleOwner, observer: Observer<String>) {
        communicationDetails.observeImportance(owner, observer)
    }

//    fun changeEnabled(enabled: Boolean) {
//        communicationDetails.mapDeadline(
//            if (enabled) StateDeadlineUI.On(
//                "дата"
//            ) else StateDeadlineUI.Off()
//        )
//    }

    fun saveTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val isDone = if (taskId == 0L) {
//                val taskSaveParam = communicationDetails.mapValueToNewTask()
//                addTaskUseCase(taskSaveParam)
            } else {
//                val taskSaveParam = communicationDetails.mapValueToEditTask()
//                editTaskUseCase(taskSaveParam)
            }
            closeScreen()
//            if (isDone is TaskResult.Success) closeScreen()
        }
    }

//    fun deleteTask() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val isDone = deleteTaskById(taskId)
//            if (isDone) closeScreen()
//        }
//    }

    @MainThread
    fun setTextTask(text: String) {
        communicationDetails.mapText(text)
    }

    @MainThread
    fun setImportanceTask(importance: String) {
        communicationDetails.mapImportance(importance)
    }

    @MainThread
    fun setDeadlineTask(time: Long) {
    }

    @MainThread
    fun closeScreen() {
        communicationDetails.mapIsClose(true)
    }

}