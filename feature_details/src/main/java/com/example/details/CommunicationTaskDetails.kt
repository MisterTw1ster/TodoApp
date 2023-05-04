package com.example.details

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.core.DateLongToString
import com.example.core_domain.models.TaskDomainParams
import com.example.details.models.TaskUI
import com.example.details.models.ModeScreenDetails
import com.example.details.models.StateDeadlineUI
import com.example.details.models.TaskSaveParamsUI


class CommunicationTaskDetails (
    private val mode: MutableLiveData<ModeScreenDetails> = MutableLiveData(),
    private val taskId: MutableLiveData<Long> = MutableLiveData(TaskUI.NEW_TASK_ID),
    private val userId: MutableLiveData<String> = MutableLiveData(""),
    private val deadline: MutableLiveData<Long> = MutableLiveData(0L),
    private val text: MutableLiveData<String> = MutableLiveData(""),
    private val importance: MutableLiveData<String> = MutableLiveData("low"),
    private val isDone: MutableLiveData<Boolean> = MutableLiveData(false),
    private val deadlineState: MutableLiveData<StateDeadlineUI> = MutableLiveData(),
    private val dateLongToString: DateLongToString
) {

     fun getSaveParams(): TaskSaveParamsUI {
        val mode = mode.value!!
        val taskParams = TaskDomainParams(
            id = taskId.value!!, text = text.value!!,
            importance = importance.value!!, deadline = deadline.value!!,
            isDone = isDone.value!!, userId = userId.value!!
        )
        return TaskSaveParamsUI(mode, taskParams)
    }

    fun observeMode(owner: LifecycleOwner, observer: Observer<ModeScreenDetails>) {
        mode.observe(owner, observer)
    }

    fun observeDeadlineState(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>) =
        deadlineState.observe(owner, observer)

    fun observeIsDone(owner: LifecycleOwner, observer: Observer<Boolean>) {
        isDone.observe(owner, observer)
    }

    fun observeText(owner: LifecycleOwner, observer: Observer<String>) {
        text.observe(owner, observer)
    }

    fun observeImportance(owner: LifecycleOwner, observer: Observer<String>) {
        importance.observe(owner, observer)
    }

    fun mapMode(source: ModeScreenDetails) {
        mode.postValue(source)
    }

    fun mapTaskID(source: Long) {
        taskId.postValue(source)
    }

    fun mapUserID(source: String) {
        userId.postValue(source)
    }

    fun mapText(source: String) {
        text.postValue(source)
    }

    fun mapImportance(source: String) {
        importance.postValue(source)
    }

    fun initDeadline(source: Long) {
        deadline.postValue(source)
        val time = dateLongToString.ddMMMMyyyy(source)
        val initStateDeadline =
            if (time != null) StateDeadlineUI.Success(time) else StateDeadlineUI.Empty
        deadlineState.postValue(
            StateDeadlineUI.Initial(source != 0L, initStateDeadline)
        )
    }

    fun mapDeadline(source: Long) {
        deadline.postValue(source)
        val deadlineText = dateLongToString.ddMMMMyyyy(source)
        if (deadlineText == null) {
            deadlineState.postValue((StateDeadlineUI.Empty))
        } else {
            deadlineState.postValue(StateDeadlineUI.Success(deadlineText))
        }
    }

    fun mapIsDone(source: Boolean) {
        isDone.postValue(source)
    }

}

