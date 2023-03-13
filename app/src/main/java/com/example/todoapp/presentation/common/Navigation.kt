package com.example.todoapp.presentation.common

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.di.scope.AppScope
import javax.inject.Inject

@AppScope
class Navigation @Inject constructor() {

    fun authFragment(fragment: Fragment) {
        fragment.findNavController().navigate(
            TASKS_TO_AUTH_ACTION_ID
        )
    }

    fun tasksFragment(fragment: Fragment, userId: String) {
        fragment.findNavController().navigate(
            AUTH_TO_TASKS_ACTION_ID,
            bundleOf("userId" to userId)
        )
    }

    fun newDetailsFragment(fragment: Fragment) {
        fragment.findNavController().navigate(
            TASKS_TO_DETAILS_ACTION_ID
        )
    }

    fun editDetailsFragment(fragment: Fragment, taskID: Long) {
        fragment.findNavController().navigate(
            TASKS_TO_DETAILS_ACTION_ID,
            bundleOf("taskID" to taskID)
        )
    }

    fun popFragment(fragment: Fragment) {
        fragment.findNavController().popBackStack()
    }

    companion object {
        private const val AUTH_TO_TASKS_ACTION_ID = R.id.action_authFragment_to_tasks
        private const val TASKS_TO_AUTH_ACTION_ID = R.id.action_tasks_to_authFragment
        private const val TASKS_TO_DETAILS_ACTION_ID = R.id.action_tasks_to_detailsFragment
    }
}