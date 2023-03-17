package com.example.todoapp.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.models.UserDomain
import com.example.todoapp.presentation.common.navigation.newnav.NavigationCommunication
import com.example.todoapp.presentation.common.navigation.newnav.NavigationStrategy
import com.example.todoapp.presentation.common.navigation.newnav.Screen
import com.example.todoapp.usecase.auth.FetchUsersUseCase
import com.example.todoapp.usecase.auth.GetCurrentUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val navigationCommunication: NavigationCommunication.Base,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val fetchUsersUseCase: FetchUsersUseCase
) : ViewModel() {

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return

        viewModelScope.launch(Dispatchers.IO) {
            val currentUser = getCurrentUserUseCase()
            val usersDomain = fetchUsersUseCase()
            ChooseStartScreen(navigationCommunication, currentUser, usersDomain).map()
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<NavigationStrategy>) =
        navigationCommunication.observe(owner, observer)

}

class ChooseStartScreen(
    private val navigationCommunication: NavigationCommunication.Base,
    private val currentUser: UserDomain?,
    private val localUsers: List<UserDomain>
) {
    private val selectUser by lazy { navigationCommunication.map(NavigationStrategy.Replace(Screen.SelectUser)) }
    private val auth by lazy { navigationCommunication.map(NavigationStrategy.Replace(Screen.Auth)) }
    private val tasks by lazy {
        navigationCommunication.map(
            NavigationStrategy.Replace(
                Screen.Tasks(
                    currentUser!!.localId
                )
            )
        )
    }

    suspend fun map() = withContext(Dispatchers.Main) {
        currentUser?.apply { tasks } ?: localUsers.takeIf { users -> users.isEmpty() }
            ?.apply { auth } ?: selectUser
    }
}