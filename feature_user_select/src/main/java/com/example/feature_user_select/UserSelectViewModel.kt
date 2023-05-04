package com.example.feature_user_select

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.navigation.Navigation
import com.example.core_domain.usecase.users.FetchUsersUseCase
import com.example.core_domain.usecase.users.SetCurrentUserIdUseCase
import com.example.feature_user_select.mappers.UserDomainToUIMapper
import com.example.feature_user_select.rvusers.UserSelectUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class UserSelectViewModel(
    private val communication: CommunicationUserSelect,
    private val navigation: Navigation,
    private val setCurrentUserIdUseCase: SetCurrentUserIdUseCase,
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val domainToUIMapper: UserDomainToUIMapper,
): ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch(Dispatchers.IO) {
            val usersDomain = fetchUsersUseCase()
            communication.mapUsers(usersDomain.map { user ->
                domainToUIMapper.transform(user)
            })
        }
    }

    fun selectUser(user: UserSelectUI) {
        navigation.showListReplace(user.localId)
        scope.launch {
            setCurrentUserIdUseCase(user.localId)
        }
    }

    fun showAddUser() {
        navigation.showAuthUserAdd()
    }

    fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserSelectUI>>) {
        communication.observeUsers(owner, observer)
    }

}



