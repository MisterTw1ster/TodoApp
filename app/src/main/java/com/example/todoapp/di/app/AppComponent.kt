package com.example.todoapp.di.app

import android.app.Application
import com.example.core.ManageResources
import com.example.core.di.scope.AppScope
import com.example.core.navigation.Navigation
import com.example.core_domain.usecase.settings.*
import com.example.core_domain.usecase.tasks.*
import com.example.core_domain.usecase.users.*
import com.example.feature_dialogs.taskFilter.di.TaskFilterDeps
import com.example.feature_dialogs.taskImportance.di.TaskImportanceDeps
import com.example.feature_dialogs.taskSorting.di.TaskSortingDeps
import com.example.feature_list.di.ListDeps
import com.example.feature_user_auth.di.UserAuthDeps
import com.example.feature_user_select.di.UserSelectDeps
import com.example.task_details.di.TaskDetailsDeps
import com.example.todoapp.di.mainactivity.MainActivityComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, AppBindsModule::class])
interface AppComponent : ListDeps, TaskDetailsDeps, UserSelectDeps,
        UserAuthDeps, TaskSortingDeps, TaskFilterDeps, TaskImportanceDeps
{

    override val navigation: Navigation
    override val manageResources: ManageResources
    override val observeTasksUseCase: ObserveTasksUseCase
    override val editTaskUseCase: EditTaskUseCase
    override val addTaskUseCase: AddTaskUseCase
    override val getTaskById: GetTaskByIdUseCase
    override val deleteTaskUseCase: DeleteTaskUseCase
    override val markForDeletionTaskUseCase: MarkForDeletionTaskUseCase
    override val uncheckToDeleteTaskUseCase: UncheckToDeleteTaskUseCase
    override val fetchUsersUseCase: FetchUsersUseCase
    override val setCurrentUserIdUseCase: SetCurrentUserIdUseCase
    override val getCurrentUserUseCase: GetCurrentUserUseCase
    override val signInWithEmailUseCase: SignInWithEmailUseCase
    override val signUpWithEmailUseCase: SignUpWithEmailUseCase
    override val signOutUseCase: SignOutUseCase
    override val getTasksSortingUseCase: GetTasksSortingUseCase
    override val saveTasksSortingUseCase: SaveTasksSortingUseCase
    override val fetchTasksFiltersUseCase: FetchFiltersUseCase
    override val saveHideCompletedFiltersUseCase: SaveHideCompletedFiltersUseCase
    override val observeSearchItemUseCase: ObserveSearchItemUseCase
    override val saveSearchItemUseCase: SaveSearchItemUseCase


    fun mainActivityComponent(): MainActivityComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: Application
        ): AppComponent
    }

}
