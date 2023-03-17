package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.todoapp.models.AuthResult
import com.example.todoapp.presentation.auth.mappers.UserDomainToUIMapper
import com.example.todoapp.presentation.common.navigation.newnav.NavigationCommunication
import com.example.todoapp.presentation.common.navigation.newnav.NavigationStrategy
import com.example.todoapp.presentation.common.navigation.newnav.Screen
import com.example.todoapp.usecase.auth.SetCurrentUserIdUseCase
import com.example.todoapp.usecase.auth.SignInWithEmailUseCase
import com.example.todoapp.usecase.auth.SignUpWithEmailUseCase
import kotlinx.coroutines.*

class AuthViewModel(
    private val communication: AuthCommunication,
    private val navigationCommunication: NavigationCommunication.Base,
    //    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val setCurrentUserIdUseCase: SetCurrentUserIdUseCase,
//    private val fetchUsersUseCase: FetchUsersUseCase,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
    private val domainToUIMapper: UserDomainToUIMapper
): ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {

//        viewModelScope.launch(Dispatchers.IO) {
//            val currentUser = getCurrentUserUseCase()
//            if (currentUser != null) {
//                communication.mapNavigation(NavigationGraph.authToTasks(currentUser.localId))
//            } else {
//                val usersDomain = fetchUsersUseCase()
//                if (usersDomain.isEmpty()) {
//                    communication.mapStateScreen(StateScreenUI.AddUser)
//                } else {
//                    communication.mapStateScreen(StateScreenUI.SelectUser)
//                }
//                communication.mapUsers(usersDomain.map { user ->
//                    domainToUIMapper.transform(user)
//                })
//            }
//        }

    }

    fun signInWithEmail() {
        scope.launch {
            val userDomainParams = communication.getUserDomainParams()
            val authResult = signInWithEmailUseCase(userDomainParams)
            withContext(Dispatchers.Main) {
                when (authResult) {
//                is AuthResult.Success -> communication.mapNavigation(
//                    NavigationGraph.authToTasks(
//                        authResult.user.localId
//                    )
//                )
                    is AuthResult.Success -> navigationCommunication.map(
                        NavigationStrategy.Replace(Screen.Tasks(authResult.user.localId))
                    )
                    is AuthResult.Failure -> communication.mapError(authResult.error)
                }
            }
        }
    }

    fun signUpWithEmail() {
        scope.launch {
            val userDomainParams = communication.getUserDomainParams()
            val authResult = signUpWithEmailUseCase(userDomainParams)
            withContext(Dispatchers.Main) {
                when (authResult) {
//                is AuthResult.Success -> communication.mapNavigation(
//                    NavigationGraph.authToTasks(
//                        authResult.user.localId
//                    )
//                )
                    is AuthResult.Success -> navigationCommunication.map(
                        NavigationStrategy.Replace(Screen.Tasks(authResult.user.localId))
                    )
                    is AuthResult.Failure -> communication.mapError(authResult.error)
                }
            }
            }
        }


//    fun selectUser(user: UserUI) {
//        scope.launch {
//            setCurrentUserIdUseCase(user.localId)
//            communication.mapNavigation(NavigationGraph.authToTasks(user.localId))
//        }
//    }

//    fun addUser() {
//        communication.mapStateScreen(StateScreenUI.AddUser)
//    }

    fun setLogin(login: String) {
        communication.mapLogin(login)
    }

    fun setPassword(password: String) {
        communication.mapPassword(password)
    }

//    fun observeStateScreen(owner: LifecycleOwner, observer: Observer<StateScreenUI>) {
//        communication.observeStateScreen(owner, observer)
//    }

//    fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserUI>>) {
//        communication.observeUsers(owner, observer)
//    }

    fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
        communication.observeError(owner, observer)
    }

//    fun observeNavigate(owner: LifecycleOwner, observer: Observer<NavigationGraph>) {
//        communication.observeNavigation(owner, observer)
//    }
}



