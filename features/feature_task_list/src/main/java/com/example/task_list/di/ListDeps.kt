package com.example.task_list.di

import com.example.core.ManageResources
import com.example.core.navigation.Navigation
import com.example.domain.usecase.settings.ObserveSearchItemUseCase
import com.example.domain.usecase.settings.SaveSearchItemUseCase
import com.example.domain.usecase.tasks.*
import com.example.domain.usecase.users.GetCurrentUserUseCase
import com.example.domain.usecase.users.SignOutUseCase

interface ListDeps {
    val navigation: Navigation
    val manageResources: ManageResources
    val observeTasksUseCase: ObserveTasksUseCase
    val editTaskUseCase: EditTaskUseCase
    val deleteTaskUseCase: DeleteTaskUseCase
    val markForDeletionTaskUseCase: MarkForDeletionTaskUseCase
    val uncheckToDeleteTaskUseCase: UncheckToDeleteTaskUseCase
    val getCurrentUserUseCase: GetCurrentUserUseCase
    val signOutUseCase: SignOutUseCase
    val observeSearchItemUseCase: ObserveSearchItemUseCase
    val saveSearchItemUseCase: SaveSearchItemUseCase
}