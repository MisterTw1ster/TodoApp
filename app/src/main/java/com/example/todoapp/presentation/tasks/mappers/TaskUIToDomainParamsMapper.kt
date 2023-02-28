package com.example.todoapp.presentation.tasks.mappers

import com.example.todoapp.di.scope.AppScope
import com.example.todoapp.models.TaskDomainParams
import com.example.todoapp.presentation.tasks.models.TaskUI
import javax.inject.Inject

@AppScope
class TaskUIToDomainParamsMapper @Inject constructor(){
    fun transform(task: TaskUI): TaskDomainParams {
        return TaskDomainParams(
            id = task.id,
            text = task.text,
            importance = task.importance,
            deadline = task.deadline,
            isDone = task.isDone
        )
    }
}

