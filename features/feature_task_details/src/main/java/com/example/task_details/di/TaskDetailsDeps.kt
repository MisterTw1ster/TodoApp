package com.example.task_details.di

import com.example.core.ManageResources
import com.example.core.navigation.Navigation
import com.example.domain.usecase.tasks.AddTaskUseCase
import com.example.domain.usecase.tasks.DeleteTaskUseCase
import com.example.domain.usecase.tasks.EditTaskUseCase
import com.example.domain.usecase.tasks.GetTaskByIdUseCase

interface TaskDetailsDeps {
    val navigation: Navigation
    val manageResources: ManageResources
    val addTaskUseCase: AddTaskUseCase
    val editTaskUseCase: EditTaskUseCase
    val getTaskById: GetTaskByIdUseCase
    val deleteTaskUseCase: DeleteTaskUseCase
}