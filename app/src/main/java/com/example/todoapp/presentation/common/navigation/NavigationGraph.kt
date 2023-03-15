package com.example.todoapp.presentation.common.navigation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R

sealed class NavigationGraph {

    abstract fun navigate(fragment: Fragment)

    class authToTasks(private val userId: String) : NavigationGraph() {
        override fun navigate(fragment: Fragment) {
            fragment.findNavController().navigate(
                R.id.action_authFragment_to_tasks,
                bundleOf("userId" to userId)
            )
        }
    }

    object tasksToAuth : NavigationGraph() {
        override fun navigate(fragment: Fragment) {
            fragment.findNavController().navigate(
                R.id.action_tasks_to_authFragment
            )
        }
    }
    object tasksToNewDetails : NavigationGraph() {
        override fun navigate(fragment: Fragment) {
            fragment.findNavController().navigate(
                R.id.action_tasks_to_detailsFragment,
            )
        }
    }

    class tasksToEditDetails(private val taskId: Long) : NavigationGraph() {
        override fun navigate(fragment: Fragment) {
            fragment.findNavController().navigate(
                R.id.action_tasks_to_detailsFragment,
                bundleOf("taskID" to taskId)
            )
        }
    }
}
