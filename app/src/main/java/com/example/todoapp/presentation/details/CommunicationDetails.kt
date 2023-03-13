package com.example.todoapp.presentation.details

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.models.TaskDomainParams
import com.example.todoapp.presentation.common.LongDateToString
import com.example.todoapp.presentation.details.models.StateDeadlineUI
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

interface CommunicationDetails {

    fun getTaskDomainParams(): TaskDomainParams

    fun observeText(owner: LifecycleOwner, observer: Observer<String>)
    fun observeImportance(owner: LifecycleOwner, observer: Observer<String>)
    fun observeDeadlineState(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>)
    fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>)

    fun mapTaskID(source: Long)
    fun mapText(source: String)
    fun mapImportance(source: String)
    fun initDeadline(source: Long)
    fun mapDeadline(source: Long)
    fun mapUserId(source: String)
    fun mapCloseScreen(source: Boolean)

    class Base @AssistedInject constructor(
        @Assisted("taskId") private val taskId: MutableLiveData<Long> = MutableLiveData(0L),
        @Assisted("deadline") private val deadline: MutableLiveData<Long> = MutableLiveData(0L),
        @Assisted("text") private val text: MutableLiveData<String> = MutableLiveData(""),
        @Assisted("importance") private val importance: MutableLiveData<String> = MutableLiveData("low"),
        @Assisted("isDone") private val isDone: MutableLiveData<Boolean> = MutableLiveData(false),
        @Assisted("isClose") private val isClose: MutableLiveData<Boolean> = MutableLiveData(false),
        @Assisted("userId") private val userId: MutableLiveData<String> = MutableLiveData(""),
        private val longDateToString: LongDateToString
    ) : CommunicationDetails {

        @AssistedFactory
        interface Factory {
            fun create(
                @Assisted("taskId") taskId: MutableLiveData<Long> = MutableLiveData(0L),
                @Assisted("deadline") deadline: MutableLiveData<Long> = MutableLiveData(0L),
                @Assisted("text") text: MutableLiveData<String> = MutableLiveData(""),
                @Assisted("importance") importance: MutableLiveData<String> = MutableLiveData("low"),
                @Assisted("isDone") isDone: MutableLiveData<Boolean> = MutableLiveData(false),
                @Assisted("isClose") isClose: MutableLiveData<Boolean> = MutableLiveData(false),
                @Assisted("userId") userId: MutableLiveData<String> = MutableLiveData("")
            ): Base
        }

        private val deadlineState: MutableLiveData<StateDeadlineUI> = MutableLiveData()

        override fun getTaskDomainParams(): TaskDomainParams {
            return TaskDomainParams(
                id = taskId.value!!, // TODO
                text = text.value!!, // TODO
                importance = importance.value!!, // TODO
                deadline = deadline.value!!, // TODO
                isDone = isDone.value!!, // TODO
                userId = userId.value!!
            )
        }

        override fun observeDeadlineState(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>) =
            deadlineState.observe(owner, observer)

        override fun observeText(owner: LifecycleOwner, observer: Observer<String>) {
            text.observe(owner, observer)
        }

        override fun observeImportance(owner: LifecycleOwner, observer: Observer<String>) {
            importance.observe(owner, observer)
        }

        override fun mapTaskID(source: Long) {
            taskId.postValue(source)
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
                if (time != null) StateDeadlineUI.On(time) else StateDeadlineUI.Off
            deadlineState.postValue(
                StateDeadlineUI.Initial(source != 0L, initStateDeadline)
            )
        }

        override fun mapDeadline(source: Long) {
            deadline.postValue(source)
            val deadlineText = longDateToString.ddMMMMyyyy(source)
            if (deadlineText == null) {
                deadlineState.postValue((StateDeadlineUI.Off))
            } else {
                deadlineState.postValue(StateDeadlineUI.On(deadlineText))
            }
        }

        override fun mapUserId(source: String) {
            userId.postValue(source)
        }

        override fun observeCloseScreen(owner: LifecycleOwner, observer: Observer<Boolean>) {
            isClose.observe(owner, observer)
        }

        override fun mapCloseScreen(source: Boolean) {
            isClose.postValue(source)
        }

    }
}
