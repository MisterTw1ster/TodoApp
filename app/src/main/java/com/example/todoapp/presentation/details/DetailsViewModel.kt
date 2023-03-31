package com.example.todoapp.presentation.details

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.presentation.common.navigation.newnav.NavigationCommunication
import com.example.todoapp.presentation.common.navigation.newnav.NavigationStrategy
import com.example.todoapp.presentation.common.navigation.newnav.ScreenModal
import com.example.todoapp.presentation.details.models.ModeScreenDetails
import com.example.todoapp.presentation.details.models.StateDeadlineUI
import com.example.todoapp.presentation.tasks.models.TaskUI
import com.example.todoapp.usecase.tasks.AddTaskUseCase
import com.example.todoapp.usecase.tasks.DeleteTaskByIdUseCase
import com.example.todoapp.usecase.tasks.EditTaskUseCase
import com.example.todoapp.usecase.tasks.GetTaskByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val taskId: Long,
    private val userId: String,
    private val communicationDetails: CommunicationDetails,
    private val navigationCommunication: NavigationCommunication.Base,
    private val addTaskUseCase: AddTaskUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val getTaskByIDUseCase: GetTaskByIdUseCase,
    private val deleteTaskById: DeleteTaskByIdUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch(Dispatchers.IO) {
            val mode = ChooseModeScreenDetails(taskId).map()
            communicationDetails.mapMode(mode)
            communicationDetails.mapTaskID(taskId)
            communicationDetails.mapUserID(userId)
            var deadlineInit = NEW_TASK_DEADLINE
            if (mode == ModeScreenDetails.Edit) {
                val taskDomain = getTaskByIDUseCase(taskId)
                with(taskDomain) {
                    communicationDetails.mapText(text)
                    communicationDetails.mapImportance(importance)
                    communicationDetails.mapIsDone(isDone)
                    deadlineInit = deadline

                }
            }
            communicationDetails.initDeadline(deadlineInit)
        }
    }


    fun saveTask() {
        scope.launch {
            val saveParams = communicationDetails.getSaveParams()
            when (saveParams.mode) {
                ModeScreenDetails.New -> addTaskUseCase(saveParams.taskParams)
                else -> editTaskUseCase(saveParams.taskParams)
            }
        }
        closeScreen()
    }

    fun deleteTask() {
        scope.launch {
            val saveParams = communicationDetails.getSaveParams()
            deleteTaskById(saveParams.taskParams)
        }
        closeScreen()
    }

    fun observeModeScreenDetails(owner: LifecycleOwner, observer: Observer<ModeScreenDetails>) {
        communicationDetails.observeMode(owner, observer)
    }

    fun observeImportance(owner: LifecycleOwner, observer: Observer<String>) {
        communicationDetails.observeImportance(owner, observer)
    }

    fun observeDeadlineState(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>) {
        communicationDetails.observeDeadlineState(owner, observer)
    }

    fun observeText(owner: LifecycleOwner, observer: Observer<String>) {
        communicationDetails.observeText(owner, observer)
    }

    fun observeIsDone(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationDetails.observeIsDone(owner, observer)
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

    fun setIsDone(isDone: Boolean) {
        communicationDetails.mapIsDone(isDone)
    }

    fun showImportanceModal() {
        val screen = ScreenModal.Importance
        navigationCommunication.map(NavigationStrategy.Modal(screen))
    }

    fun closeScreen() {
        navigationCommunication.map(NavigationStrategy.Pop)
    }

    companion object {
        private const val NEW_TASK_DEADLINE = 0L
    }
}

class ChooseModeScreenDetails(private val source: Long) {
    private val new: ModeScreenDetails by lazy { ModeScreenDetails.New }
    private val edit: ModeScreenDetails by lazy { ModeScreenDetails.Edit }
    fun map(): ModeScreenDetails = if (source == TaskUI.NEW_TASK_ID) new else edit
}

