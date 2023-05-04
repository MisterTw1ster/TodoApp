package com.example.components.presentation.navigation

import com.example.core.navigation.Navigation
import com.example.todoapp.presentation.navigation.CommunicationNavigation

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

    override fun showTasksSortingModal() {
        communicationNavigation.map(NavigationStrategy.Modal(ScreenModal.Sorting))
    }

    override fun showTasksFiltersModal() {
        communicationNavigation.map(NavigationStrategy.Modal(ScreenModal.Filters))
    }

    override fun showImportanceTaskDetails() {
        communicationNavigation.map(NavigationStrategy.Modal(ScreenModal.Importance))
    }


    override fun pop() {
        communicationNavigation.map(NavigationStrategy.Pop)
    }


}
