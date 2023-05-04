package com.example.feature_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.navigation.Navigation
import com.example.core_domain.usecase.settings.ObserveSearchItemUseCase
import com.example.core_domain.usecase.settings.SaveSearchItemUseCase
import com.example.core_domain.usecase.tasks.*
import com.example.core_domain.usecase.users.GetCurrentUserUseCase
import com.example.core_domain.usecase.users.SignOutUseCase
import com.example.feature_list.mappers.SearchItemDomainToUiMapper
import com.example.feature_list.mappers.SearchItemUiToDomainMapper
import com.example.feature_list.mappers.TaskDomainToUIMapper
import com.example.feature_list.mappers.TaskUIToDomainParamsMapper
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ListViewModelFactory @AssistedInject constructor(
    @Assisted("userId") private val userId: String,
    private val communication: CommunicationList,
    private val navigation: Navigation,
    private val domainToUIMapper: TaskDomainToUIMapper,
    private val uiToDomainParamsMapper: TaskUIToDomainParamsMapper,
    private val searchItemDomainToUiMapper: SearchItemDomainToUiMapper,
    private val searchItemUiToDomainMapper: SearchItemUiToDomainMapper,
    private val observeTasksUseCase: ObserveTasksUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val markForDeletionTaskUseCase: MarkForDeletionTaskUseCase,
    private val uncheckToDeleteTaskUseCase: UncheckToDeleteTaskUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val observeSearchItemUseCase: ObserveSearchItemUseCase,
    private val saveSearchItemUseCase: SaveSearchItemUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(
            userId,
            communication,
            navigation,
            domainToUIMapper,
            uiToDomainParamsMapper,
            searchItemDomainToUiMapper,
            searchItemUiToDomainMapper,
            observeTasksUseCase,
            editTaskUseCase,
            deleteTaskUseCase,
            markForDeletionTaskUseCase,
            uncheckToDeleteTaskUseCase,
            getCurrentUserUseCase,
            signOutUseCase,
            observeSearchItemUseCase,
            saveSearchItemUseCase
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("userId") userId: String
        ): ListViewModelFactory
    }
}