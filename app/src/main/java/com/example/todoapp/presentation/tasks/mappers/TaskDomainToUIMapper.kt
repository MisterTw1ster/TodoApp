package com.example.todoapp.presentation.tasks.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.todoapp.R
import com.example.todoapp.di.scope.AppScope
import com.example.todoapp.models.TaskDomain
import com.example.todoapp.presentation.tasks.models.TaskUI
import com.example.todoapp.presentation.common.LongDateToString
import com.example.todoapp.presentation.common.ManageResources
import javax.inject.Inject

@AppScope
class TaskDomainToUIMapper @Inject constructor(
    private val longDateToString: LongDateToString,
    private val chooseImportanceColor: ChooseImportanceColor
){
    fun transform(task: TaskDomain): TaskUI {
        return TaskUI(
            id = task.id,
            text = task.text,
            importance = task.importance,
            deadline = task.deadline,
            isDone = task.isDone,
            color = chooseImportanceColor.map(task.importance),
            deadlineText = longDateToString.ddMMMMyyyy(task.deadline)
        )
    }
}

@AppScope
class ChooseImportanceColor @Inject constructor(
    private val resources: ManageResources
){
    private val default: Int by lazy { resources.color(R.color.color_white) }
    private val low: Int by lazy { resources.color(R.color.color_green) }
    private val important: Int by lazy { resources.color(R.color.color_red) }
    fun map(source: String) = when(source) {
        "low" -> low
        "important" -> important
        else -> default
    }
}