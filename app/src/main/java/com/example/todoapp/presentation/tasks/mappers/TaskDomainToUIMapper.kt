package com.example.todoapp.presentation.tasks.mappers

import com.example.todoapp.datasource.TaskData
import com.example.todoapp.models.TaskDomain
import com.example.todoapp.presentation.tasks.models.TaskUI
import javax.inject.Inject

class TaskDomainToUIMapper @Inject constructor(){
    fun transform(task: TaskDomain): TaskUI {
        val color = when(task.importance) {
            "low" -> "#34C759"
            "important" -> "#FF3B30"
            else -> "#FFFFFF"

        }
        return TaskUI(
            id = task.id,
            text = task.text,
            importance = task.importance,
            deadline = task.deadline,
            isDone = task.isDone,
            color = color
        )
    }
}