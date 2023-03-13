package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.models.UserDomain
import com.example.todoapp.usecase.auth.FetchUsersUseCase
import com.example.todoapp.usecase.auth.SignInWithEmailUseCase
import com.example.todoapp.usecase.auth.SignUpWithEmailUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AuthViewModel(
    private val communication: CommunicationAuth,
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
): ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            communication.mapUsers(fetchUsersUseCase())
        }
    }

    fun signInWithEmail(email: String, password: String) {
        scope.launch { signInWithEmailUseCase(email, password) }
    }

    fun signUpWithEmail(email: String, password: String) {
        scope.launch() { signUpWithEmailUseCase(email, password) }
    }

    fun observeUsers(owner: LifecycleOwner, observer: Observer<List<UserDomain>>) {
        communication.observeUsers(owner, observer)
    }

    fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
        communication.observeError(owner, observer)
    }
}



