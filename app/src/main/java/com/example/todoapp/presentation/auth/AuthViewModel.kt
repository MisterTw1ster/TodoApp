package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.models.AuthResult
import com.example.todoapp.presentation.auth.mappers.UserDomainToUIMapper
import com.example.todoapp.presentation.auth.models.UserUI
import com.example.todoapp.presentation.common.navigation.NavigationGraph
import com.example.todoapp.usecase.auth.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AuthViewModel(
    private val communication: CommunicationAuth,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val setCurrentUserIdUseCase: SetCurrentUserIdUseCase,
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
    private val domainToUIMapper: UserDomainToUIMapper
): ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {

        viewModelScope.launch(Dispatchers.IO) {
            val currentUser = getCurrentUserUseCase()
            if (currentUser != null) {
                communication.mapNavigation(NavigationGraph.authToTasks(currentUser.localId))
//                navigation.tasksFragment()
            } else {
                val usersDomain = fetchUsersUseCase()
                communication.mapUsers(usersDomain.map { user ->
                    domainToUIMapper.transform(user)
                })
            }
        }

    }

    fun signInWithEmail() {
        scope.launch {
            val userDomainParams = communication.getUserDomainParams()
            when (val authResult = signInWithEmailUseCase(userDomainParams)) {
                is AuthResult.Success -> communication.mapNavigation(
                    NavigationGraph.authToTasks(
                        authResult.user.localId
                    )
                )
                is AuthResult.Failure -> communication.mapError(authResult.error)
            }
        }
    }

    fun signUpWithEmail() {
        scope.launch {
            val userDomainParams = communication.getUserDomainParams()
            when (val authResult = signUpWithEmailUseCase(userDomainParams)) {
                is AuthResult.Success -> communication.mapNavigation(
                    NavigationGraph.authToTasks(
                        authResult.user.localId
                    )
                )
                is AuthResult.Failure -> communication.mapError(authResult.error)
            }
        }
    }

    fun selectUser(user: UserUI) {
        scope.launch {
            setCurrentUserIdUseCase(user.localId)
            communication.mapNavigation(NavigationGraph.authToTasks(user.localId))
        }
    }

    fun setLogin(login: String) {
        communication.mapLogin(login)
    }

    fun setPassword(password: String) {
        communication.mapPassword(password)
    }

    fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserUI>>) {
        communication.observeUsers(owner, observer)
    }

    fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
        communication.observeError(owner, observer)
    }

    fun observeNavigate(owner: LifecycleOwner, observer: Observer<NavigationGraph>) {
        communication.observeNavigation(owner, observer)
    }
}



