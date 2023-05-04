package com.example.feature_list.mappers

import com.example.core_domain.models.TaskDomainParams
import com.example.feature_list.rvlist.TaskUI
import dagger.Reusable
import javax.inject.Inject

@Reusable
class TaskUIToDomainParamsMapper @Inject constructor(){
    fun transform(task: TaskUI): TaskDomainParams {
        return TaskDomainParams(
            id = task.id,
            text = task.text,
            importance = task.importance,
            deadline = task.deadline,
            isDone = task.isDone,
            userId = task.userId
        )
    }
}

