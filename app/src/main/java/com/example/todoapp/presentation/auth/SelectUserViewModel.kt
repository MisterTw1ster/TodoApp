package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.presentation.auth.mappers.UserDomainToUIMapper
import com.example.todoapp.presentation.auth.models.UserUI
import com.example.todoapp.presentation.common.navigation.newnav.NavigationCommunication
import com.example.todoapp.presentation.common.navigation.newnav.NavigationStrategy
import com.example.todoapp.presentation.common.navigation.newnav.Screen
import com.example.todoapp.usecase.auth.FetchUsersUseCase
import com.example.todoapp.usecase.auth.SetCurrentUserIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SelectUserViewModel(
    private val communication: SelectUserCommunication,
    private val navigationCommunication: NavigationCommunication.Base,
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

    fun selectUser(user: UserUI) {
        navigationCommunication.map(NavigationStrategy.Replace(Screen.Tasks(user.localId)))
        scope.launch {
            setCurrentUserIdUseCase(user.localId)
        }
    }

    fun showAddUser() {
        navigationCommunication.map(NavigationStrategy.Add(Screen.Auth))
    }

    fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserUI>>) {
        communication.observeUsers(owner, observer)
    }

}



