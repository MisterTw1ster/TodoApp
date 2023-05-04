package com.example.feature_list.mappers

import com.example.core.DateLongToString
import com.example.core.ManageResources
import com.example.core_domain.models.TaskDomain
import com.example.core.R
import com.example.feature_list.rvlist.TaskUI
import dagger.Reusable
import javax.inject.Inject

@Reusable
class TaskDomainToUIMapper @Inject constructor(
    private val dateLongToString: DateLongToString,
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
            deadlineText = dateLongToString.ddMMMMyyyy(task.deadline),
            userId = task.userId
        )
    }
}

@Reusable
class ChooseImportanceColor @Inject constructor(
    private val resources: ManageResources
){
    private val default: Int by lazy { resources.color(R.color.back_primary) }
    private val low: Int by lazy { resources.color(R.color.color_green) }
    private val important: Int by lazy { resources.color(R.color.color_coral) }
    fun map(source: String) = when(source) {
        "low" -> low
        "important" -> important
        else -> default
    }
}