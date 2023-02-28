package com.example.todoapp.presentation.details

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.todoapp.models.TaskDomain
import com.example.todoapp.models.TaskDomainParams
import com.example.todoapp.presentation.details.models.StateDeadlineUI

interface CommunicationDetails {

    fun observeDeadline(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>)
    fun observeText(owner: LifecycleOwner, observer: Observer<String>)
    fun observeImportance(owner: LifecycleOwner, observer: Observer<String>)
    fun observeIsClose(owner: LifecycleOwner, observer: Observer<Boolean>)

    fun initTask(task: TaskDomain)
    fun mapDeadline(source: StateDeadlineUI)
    fun mapText(source: String)
    fun mapImportance(source: String)
    fun mapIsClose(source: Boolean)
    fun getTaskDomainParams(): TaskDomainParams
//    fun mapValueToEditTask(): EditTaskDomainParam

    class Base (
        private val taskId: MutableLiveData<Long> = MutableLiveData(0L),
        private val deadline: MutableLiveData<StateDeadlineUI> = MutableLiveData(),
        private val text: MutableLiveData<String> = MutableLiveData(""),
        private val importance: MutableLiveData<String> = MutableLiveData("low"),
        private val isClose: MutableLiveData<Boolean> = MutableLiveData(false)
    ) : CommunicationDetails {

        override fun observeDeadline(owner: LifecycleOwner, observer: Observer<StateDeadlineUI>) =
            deadline.observe(owner, observer)

        override fun observeText(owner: LifecycleOwner, observer: Observer<String>) {
            text.observe(owner, observer)
        }

        override fun observeImportance(owner: LifecycleOwner, observer: Observer<String>) {
            importance.observe(owner, observer)
        }

        override fun observeIsClose(owner: LifecycleOwner, observer: Observer<Boolean>) {
            isClose.observe(owner, observer)
        }

        override fun initTask(task: TaskDomain) {
            taskId.postValue(task.id)
            text.postValue(task.text)
            importance.postValue(task.importance)
            val time = task.deadline.toString()//longDateToString.ddMMMMyyyy(task.deadline) // TODO
            val initStateDeadline =
                if (task.deadline != 0L) StateDeadlineUI.On(time) else StateDeadlineUI.Off()
            deadline.postValue(
                StateDeadlineUI.Initial(task.deadline != 0L, initStateDeadline)
            )
        }

        override fun mapDeadline(source: StateDeadlineUI) {
            deadline.postValue(source)
        }

        override fun mapText(source: String) {
            text.postValue(source)
        }

        override fun mapImportance(source: String) {
            importance.postValue(source)
        }

        override fun mapIsClose(source: Boolean) {
            isClose.postValue(source)
        }

        override fun getTaskDomainParams(): TaskDomainParams {
            return TaskDomainParams(
                id = taskId.value!!,
                text = text.value!!, // TODO
                importance = importance.value!!, // TODO
                deadline = 0L, // TODO
                isDone = false
            )
        }
//
//        override fun mapValueToEditTask(): EditTaskDomainParam {
//            return EditTaskDomainParam(
//                id = taskId.value!!, // TODO
//                text = text.value!!, // TODO
//                importance = importance.value!!, // TODO
//                deadline = 0L, //deadline.value!!, // TODO
//                isDone = false,
//                color = "FFFFFF",
//                isFavorite = false
//            )
//        }

    }
}