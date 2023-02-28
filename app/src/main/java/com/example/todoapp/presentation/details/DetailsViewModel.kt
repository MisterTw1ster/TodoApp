//package com.example.todoapp.presentation.details
//
//import androidx.annotation.MainThread
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.todolistsandtasks.domain.TaskResult
//import com.example.todolistsandtasks.domain.usecase.tasks.DeleteTaskByIdUseCase
//import com.example.todolistsandtasks.domain.usecase.tasks.GetTaskByIdUseCase
//import com.example.todolistsandtasks.domain.usecase.tasks.AddNewTaskUseCase
//import com.example.todolistsandtasks.domain.usecase.tasks.EditTaskUseCase
//import com.example.todolistsandtasks.presentation.taskdetails.viewstate.StateDeadlineUI
//import com.example.todolistsandtasks.presentation.utils.LongDateToString
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class DetailsViewModel(
//    private val taskId: String?,
//    private val communicationDetails: CommunicationDetails,
//    private val addNewTaskUseCase: AddNewTaskUseCase,
//    private val editTaskUseCase: EditTaskUseCase,
//    private val getTaskByIDUseCase: GetTaskByIdUseCase,
//    private val deleteTaskById: DeleteTaskByIdUseCase,
//    private val longDateToString: LongDateToString
//) : ViewModel() {
//
//    init {
//
//        taskId?.let {
//            viewModelScope.launch(Dispatchers.IO) {
//                val taskDomain = getTaskByIDUseCase(it)
//                communicationDetails.initTask(taskDomain)
//            }
//        } ?: run {
//            communicationDetails.mapDeadline(
//                StateDeadlineUI.Initial(
//                    false,
//                    StateDeadlineUI.Off()
//                )
//            )
//        }
//    }
//
//    fun observeStateDeadline(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>) {
//        communicationDetails.observeDeadline(owner, observer)
//    }
//
//    fun observeIsClose(owner: LifecycleOwner, observer: Observer<Boolean>) {
//        communicationDetails.observeIsClose(owner, observer)
//    }
//
//    fun observeText(owner: LifecycleOwner, observer: Observer<String>) {
//        communicationDetails.observeText(owner, observer)
//    }
//
//    fun observeImportance(owner: LifecycleOwner, observer: Observer<String>) {
//        communicationDetails.observeImportance(owner, observer)
//    }
//
//    fun changeEnabled(enabled: Boolean) {
//        communicationDetails.mapDeadline(
//            if (enabled) StateDeadlineUI.On(
//                "дата"
//            ) else StateDeadlineUI.Off()
//        )
//    }
//
//    fun saveTask() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val isDone = if (taskId == null) {
//                val taskSaveParam = communicationDetails.mapValueToNewTask()
//                addNewTaskUseCase(taskSaveParam)
//            } else {
//                val taskSaveParam = communicationDetails.mapValueToEditTask()
//                editTaskUseCase(taskSaveParam)
//            }
//            if (isDone is TaskResult.Success) closeScreen()
//        }
//    }
//
//    fun deleteTask() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val isDone = deleteTaskById(taskId)
//            if (isDone) closeScreen()
//        }
//    }
//
//    @MainThread
//    fun setTextTask(text: String) {
//        communicationDetails.mapText(text)
//    }
//
//    @MainThread
//    fun setImportanceTask(importance: String) {
//        communicationDetails.mapImportance(importance)
//    }
//
//    @MainThread
//    fun setDeadlineTask(time: Long) {
//    }
//
//    @MainThread
//    fun closeScreen() {
//        communicationDetails.mapIsClose(true)
//    }
//
//}