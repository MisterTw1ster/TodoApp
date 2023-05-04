package com.example.details

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.navigation.Navigation
import com.example.core_domain.usecase.tasks.AddTaskUseCase
import com.example.core_domain.usecase.tasks.DeleteTaskUseCase
import com.example.core_domain.usecase.tasks.EditTaskUseCase
import com.example.core_domain.usecase.tasks.GetTaskByIdUseCase
import com.example.details.models.ModeScreenDetails
import com.example.details.models.StateDeadlineUI
import com.example.details.models.TaskUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val taskId: Long,
    private val userId: String,
    private val communicationTaskDetails: CommunicationTaskDetails,
    private val navigation: Navigation,
    private val addTaskUseCase: AddTaskUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val getTaskByIDUseCase: GetTaskByIdUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch(Dispatchers.IO) {
            val mode = ChooseModeScreenDetails(taskId).map()
            communicationTaskDetails.mapMode(mode)
            communicationTaskDetails.mapTaskID(taskId)
            communicationTaskDetails.mapUserID(userId)
            var deadlineInit = NEW_TASK_DEADLINE
            if (mode == ModeScreenDetails.Edit) {
                val taskDomain = getTaskByIDUseCase(taskId)
                with(taskDomain) {
                    communicationTaskDetails.mapText(text)
                    communicationTaskDetails.mapImportance(importance)
                    communicationTaskDetails.mapIsDone(isDone)
                    deadlineInit = deadline

                }
            }
            communicationTaskDetails.initDeadline(deadlineInit)
        }
    }


    fun saveTask() {
        scope.launch {
            val saveParams = communicationTaskDetails.getSaveParams()
            when (saveParams.mode) {
                ModeScreenDetails.New -> addTaskUseCase(saveParams.taskParams)
                else -> editTaskUseCase(saveParams.taskParams)
            }
        }
        closeScreen()
    }

    fun deleteTask() {
        scope.launch {
            val saveParams = communicationTaskDetails.getSaveParams()
            deleteTaskUseCase(saveParams.taskParams)
        }
        closeScreen()
    }

    fun observeModeScreenDetails(owner: LifecycleOwner, observer: Observer<ModeScreenDetails>) {
        communicationTaskDetails.observeMode(owner, observer)
    }

    fun observeImportance(owner: LifecycleOwner, observer: Observer<String>) {
        communicationTaskDetails.observeImportance(owner, observer)
    }

    fun observeDeadlineState(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>) {
        communicationTaskDetails.observeDeadlineState(owner, observer)
    }

    fun observeText(owner: LifecycleOwner, observer: Observer<String>) {
        communicationTaskDetails.observeText(owner, observer)
    }

    fun observeIsDone(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communicationTaskDetails.observeIsDone(owner, observer)
    }

    fun setTextTask(text: String) {
        communicationTaskDetails.mapText(text)
    }

    fun setImportanceTask(importance: String) {
        communicationTaskDetails.mapImportance(importance)
    }

    fun setDeadlineTask(time: Long = 0L) {
        communicationTaskDetails.mapDeadline(time)
    }

    fun setIsDone(isDone: Boolean) {
        communicationTaskDetails.mapIsDone(isDone)
    }

    fun showImportanceModal() {
        navigation.showImportanceTaskDetails()
    }

    fun closeScreen() {
        navigation.pop()
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

