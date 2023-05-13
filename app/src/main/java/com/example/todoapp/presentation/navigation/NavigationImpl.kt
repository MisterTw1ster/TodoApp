package com.example.todoapp.presentation.navigation

import com.example.core.navigation.Navigation

class NavigationImpl(
    private val communicationNavigation: CommunicationNavigation
) : Navigation {

    override fun showListReplace(userId: String) {
        communicationNavigation.map(NavigationStrategy.Replace(Screen.List(userId)))
    }

    override fun showTaskDetailsAdd(userId: String, taskId: Long) {
        communicationNavigation.map(NavigationStrategy.Add(Screen.TaskDetails(userId, taskId)))
    }

    override fun showUserSelectReplace() {
        communicationNavigation.map(NavigationStrategy.Replace(Screen.UserSelect))
    }

    override fun showAuthUserReplace() {
        communicationNavigation.map(NavigationStrategy.Replace(Screen.UserAuth))
    }

    override fun showAuthUserAdd() {
        communicationNavigation.map(NavigationStrategy.Add(Screen.UserAuth))
    }

    override fun showImportanceTaskDetails() {
        communicationNavigation.map(NavigationStrategy.Modal(ScreenModal.Importance))
    }

    override fun pop() {
        communicationNavigation.map(NavigationStrategy.Pop)
    }

    override fun showFilter() {
        communicationNavigation.map(NavigationStrategy.Modal(ScreenModal.Filter))
    }

    override fun showSorting() {
        communicationNavigation.map(NavigationStrategy.Modal(ScreenModal.Sorting))
    }
}
