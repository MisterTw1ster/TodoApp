package com.example.todoapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.presentation.navigation.CommunicationNavigation
import com.example.todoapp.presentation.navigation.NavigationStrategy
import com.example.core.navigation.Navigation
import com.example.domain.usecase.users.FetchUsersUseCase
import com.example.domain.usecase.users.GetCurrentUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val navigation: Navigation,
    private val communicationNavigation: CommunicationNavigation,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val fetchUsersUseCase: FetchUsersUseCase
) : ViewModel() {

    fun init(isFirstRun: Boolean) {
        if (!isFirstRun) return
        viewModelScope.launch(Dispatchers.IO) {
            val currentUser = getCurrentUserUseCase()
            val localUsers = fetchUsersUseCase()
            withContext(Dispatchers.Main) {
                if (currentUser != null) {
                    navigation.showListReplace(currentUser.localId)
                } else if (localUsers.isEmpty()) {
                    navigation.showAuthUserReplace()
                } else {
                    navigation.showUserSelectReplace()
                }
            }
//            ChooseStartScreen(navigation, currentUser, localUsers).map()
            }
        }
//        navigation.showListReplace("xcvxcv")


    fun observe(owner: LifecycleOwner, observer: Observer<NavigationStrategy>) =
        communicationNavigation.observe(owner, observer)

}

//class ChooseStartScreen(
//    private val navigation: Navigation,
//    private val currentUser: UserDomain?,
//    private val localUsers: List<UserDomain>
//) {
//    private val userSelect by lazy { navigation.showUserSelectReplace() }
//    private val userAuth by lazy { navigation.showAuthUserReplace() }
//    private val tasks by lazy { navigation.showListReplace(currentUser!!.localId)}
//    suspend fun map() = withContext(Dispatchers.Main) {
//        navigationCommunication.map(
//            currentUser?.let { tasks } ?: localUsers.takeIf { users -> users.isEmpty() }
//                ?.let { userAuth } ?: userSelect
//        )
//    }
//}