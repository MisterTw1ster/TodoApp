package com.example.todoapp.presentation.details

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.models.TaskDomainParams
import com.example.todoapp.presentation.common.LongDateToString
import com.example.todoapp.presentation.details.models.ModeScreenDetails
import com.example.todoapp.presentation.details.models.StateDeadlineUI
import com.example.todoapp.presentation.details.models.TaskSaveParamsUI
import com.example.todoapp.presentation.tasks.models.TaskUI
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

interface CommunicationDetails {

    fun getSaveParams(): TaskSaveParamsUI

    fun observeMode(owner: LifecycleOwner, observer: Observer<ModeScreenDetails>)
    fun observeText(owner: LifecycleOwner, observer: Observer<String>)
    fun observeImportance(owner: LifecycleOwner, observer: Observer<String>)
    fun observeDeadlineState(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>)
    fun observeIsDone(owner: LifecycleOwner, observer: Observer<Boolean>)

    fun mapMode(source: ModeScreenDetails)
    fun mapTaskID(source: Long)
    fun mapUserID(source: String)
    fun mapText(source: String)
    fun mapImportance(source: String)
    fun initDeadline(source: Long)
    fun mapDeadline(source: Long)
    fun mapIsDone(source: Boolean)

    class Base @AssistedInject constructor(
        @Assisted("mode") private val mode: MutableLiveData<ModeScreenDetails> = MutableLiveData(),
        @Assisted("taskId") private val taskId: MutableLiveData<Long> = MutableLiveData(TaskUI.NEW_TASK_ID),
        @Assisted("userId") private val userId: MutableLiveData<String> = MutableLiveData(""),
        @Assisted("deadline") private val deadline: MutableLiveData<Long> = MutableLiveData(0L),
        @Assisted("text") private val text: MutableLiveData<String> = MutableLiveData(""),
        @Assisted("importance") private val importance: MutableLiveData<String> = MutableLiveData("low"),
        @Assisted("isDone") private val isDone: MutableLiveData<Boolean> = MutableLiveData(false),
        private val longDateToString: LongDateToString
    ) : CommunicationDetails {

        @AssistedFactory
        interface Factory {
            fun create(
                @Assisted("mode") mode: MutableLiveData<ModeScreenDetails> = MutableLiveData(),
                @Assisted("taskId") taskId: MutableLiveData<Long> = MutableLiveData(TaskUI.NEW_TASK_ID),
                @Assisted("userId") userId: MutableLiveData<String> = MutableLiveData(""),
                @Assisted("deadline") deadline: MutableLiveData<Long> = MutableLiveData(0L),
                @Assisted("text") text: MutableLiveData<String> = MutableLiveData(""),
                @Assisted("importance") importance: MutableLiveData<String> = MutableLiveData("low"),
                @Assisted("isDone") isDone: MutableLiveData<Boolean> = MutableLiveData(false),
            ): Base
        }

        private val deadlineState: MutableLiveData<StateDeadlineUI> = MutableLiveData()

        override fun getSaveParams(): TaskSaveParamsUI {
            val mode = mode.value!!
            val taskParams = TaskDomainParams(
                id = taskId.value!!, text = text.value!!,
                importance = importance.value!!, deadline = deadline.value!!,
                isDone = isDone.value!!, userId = userId.value!!
            )
            return TaskSaveParamsUI(mode, taskParams)
        }

        override fun observeMode(owner: LifecycleOwner, observer: Observer<ModeScreenDetails>) {
            mode.observe(owner, observer)
        }

        override fun observeDeadlineState(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>) =
            deadlineState.observe(owner, observer)

        override fun observeIsDone(owner: LifecycleOwner, observer: Observer<Boolean>) {
            isDone.observe(owner, observer)
        }

        override fun observeText(owner: LifecycleOwner, observer: Observer<String>) {
            text.observe(owner, observer)
        }

        override fun observeImportance(owner: LifecycleOwner, observer: Observer<String>) {
            importance.observe(owner, observer)
        }

        override fun mapMode(source: ModeScreenDetails) {
            mode.postValue(source)
        }

        override fun mapTaskID(source: Long) {
            taskId.postValue(source)
        }

        override fun mapUserID(source: String) {
            userId.postValue(source)
        }

        override fun mapText(source: String) {
            text.postValue(source)
        }

        override fun mapImportance(source: String) {
            importance.postValue(source)
        }

        override fun initDeadline(source: Long) {
            deadline.postValue(source)
            val time = longDateToString.ddMMMMyyyy(source)
            val initStateDeadline =
                if (time != null) StateDeadlineUI.Success(time) else StateDeadlineUI.Empty
            deadlineState.postValue(
                StateDeadlineUI.Initial(source != 0L, initStateDeadline)
            )
        }

        override fun mapDeadline(source: Long) {
            deadline.postValue(source)
            val deadlineText = longDateToString.ddMMMMyyyy(source)
            if (deadlineText == null) {
                deadlineState.postValue((StateDeadlineUI.Empty))
            } else {
                deadlineState.postValue(StateDeadlineUI.Success(deadlineText))
            }
        }

        override fun mapIsDone(source: Boolean) {
            isDone.postValue(source)
        }

    }
}
