package com.example.todoapp.presentation.common

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.di.scope.AppScope
import javax.inject.Inject

@AppScope
class Navigation @Inject constructor() {

    fun newDetailsFragment(fragment: Fragment) {
        fragment.findNavController().navigate(
            DETAILS_FRAGMENT_ID
        )
    }

    fun editDetailsFragment(fragment: Fragment, taskID: Long) {
        fragment.findNavController().navigate(
            DETAILS_FRAGMENT_ID,
            bundleOf("taskID" to taskID)
        )
    }

    fun closeDetailsFragment(fragment: Fragment) {
        fragment.findNavController().popBackStack()
    }

    companion object {
        private const val DETAILS_FRAGMENT_ID = R.id.action_tasks_to_detailsFragment
    }
}