package com.example.user_select.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.navigation.Navigation
import com.example.domain.usecase.users.FetchUsersUseCase
import com.example.domain.usecase.users.SetCurrentUserIdUseCase
import com.example.user_select.mappers.UserDomainToUIMapper
import com.example.user_select.view.CommunicationUserSelect
import javax.inject.Inject

class UserSelectViewModelFactory @Inject constructor(
    private val communication: CommunicationUserSelect,
    private val navigation: Navigation,
    private val setCurrentUserUseCase: SetCurrentUserIdUseCase,
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val domainToUIMapper: UserDomainToUIMapper
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserSelectViewModel(
            communication,
            navigation,
            setCurrentUserUseCase,
            fetchUsersUseCase,
            domainToUIMapper
        ) as T
    }

}