package com.example.user_auth.viewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.core.navigation.Navigation
import com.example.domain.exception.users.UserResult
import com.example.domain.usecase.users.SignInWithEmailUseCase
import com.example.domain.usecase.users.SignUpWithEmailUseCase
import com.example.user_auth.view.CommunicationUserAuth
import kotlinx.coroutines.*

class UserAuthViewModel(
    private val communication: CommunicationUserAuth,
    private val navigation: Navigation,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
): ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun signInWithEmail() {
        scope.launch {
            val userDomainParams = communication.getUserDomainParams()
            val checkAuthResult = userDomainParams.checkAuth()
            checkAuthResult?.let {
                authResultCommunication(it)
                return@launch
            }
            val authResult = signInWithEmailUseCase(userDomainParams)
            authResultCommunication(authResult)
        }
    }

    fun signUpWithEmail() {
        scope.launch {
            val userDomainParams = communication.getUserDomainParams()
            val checkAuthResult = userDomainParams.checkAuth()
            checkAuthResult?.let {
                authResultCommunication(it)
                return@launch
            }

            val authResult = signUpWithEmailUseCase(userDomainParams)
            authResultCommunication(authResult)
            }
        }

    fun setLogin(login: String) {
        communication.mapLogin(login)
    }

    fun setPassword(password: String) {
        communication.mapPassword(password)
    }

    fun observeErrorLogin(owner: LifecycleOwner, observer: Observer<String>) {
        communication.observeErrorLogin(owner, observer)
    }

    fun observeErrorPassword(owner: LifecycleOwner, observer: Observer<String>) {
        communication.observeErrorPassword(owner, observer)
    }

    fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
        communication.observeError(owner, observer)
    }

    private suspend fun authResultCommunication(userResult: UserResult) = withContext(Dispatchers.Main) {
            when (userResult) {
                is UserResult.Success -> navigation.showListReplace(userResult.user.localId)
//                    navigationCommunication.map(
//                    NavigationStrategy.Replace(Screen.Tasks(authResult.user.localId))
//                )
                is UserResult.LoginError -> communication.mapErrorLogin(userResult.error)
                is UserResult.PasswordError -> communication.mapErrorPassword(userResult.error)
                is UserResult.Failure -> communication.mapError(userResult.error)
            }
        }

}

