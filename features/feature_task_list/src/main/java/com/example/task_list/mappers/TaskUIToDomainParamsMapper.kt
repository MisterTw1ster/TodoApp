package com.example.task_list.mappers

import com.example.domain.models.TaskDomainParams
import com.example.task_list.rvList.TaskUI
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

