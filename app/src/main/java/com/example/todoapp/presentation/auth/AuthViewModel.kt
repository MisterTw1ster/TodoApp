package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.todoapp.models.AuthResult
import com.example.todoapp.presentation.common.navigation.newnav.NavigationCommunication
import com.example.todoapp.presentation.common.navigation.newnav.NavigationStrategy
import com.example.todoapp.presentation.common.navigation.newnav.Screen
import com.example.todoapp.usecase.auth.SignInWithEmailUseCase
import com.example.todoapp.usecase.auth.SignUpWithEmailUseCase
import kotlinx.coroutines.*

class AuthViewModel(
    private val communication: AuthCommunication,
    private val navigationCommunication: NavigationCommunication.Base,
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

    private suspend fun authResultCommunication(authResult: AuthResult) = withContext(Dispatchers.Main) {
            when (authResult) {
                is AuthResult.Success -> navigationCommunication.map(
                    NavigationStrategy.Replace(Screen.Tasks(authResult.user.localId))
                )
                is AuthResult.LoginError -> communication.mapErrorLogin(authResult.error)
                is AuthResult.PasswordError -> communication.mapErrorPassword(authResult.error)
                is AuthResult.Failure -> communication.mapError(authResult.error)
            }
        }

}

