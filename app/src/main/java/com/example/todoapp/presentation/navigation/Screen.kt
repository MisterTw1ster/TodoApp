package com.example.todoapp.presentation.navigation

import androidx.fragment.app.Fragment

import com.example.feature_dialogs.taskFilter.view.TaskFilterDialogFragment
import com.example.feature_dialogs.taskImportance.view.TaskImportanceDialogFragment
import com.example.feature_dialogs.taskSorting.view.TaskSortingDialogFragment
import com.example.task_list.view.ListFragment
import com.example.user_auth.view.UserAuthFragment
import com.example.user_select.view.UserSelectFragment
import com.example.task_details.view.TaskDetailsFragment

sealed class Screen {

    abstract fun fragment(): Class<out Fragment>
    abstract fun getNewInstance(): Fragment

    class List(
        private val userId: String
    ) : Screen() {
        override fun fragment(): Class<out Fragment> = ListFragment::class.java
        override fun getNewInstance() = ListFragment.newInstance(userId)
    }

    class TaskDetails(
        private val userId: String,
        private val taskId: Long
    ) : Screen() {
        override fun fragment(): Class<out Fragment> = TaskDetailsFragment::class.java
        override fun getNewInstance() = TaskDetailsFragment.newInstance(userId, taskId)
    }

    object UserSelect: Screen() {
        override fun fragment(): Class<out Fragment> = UserSelectFragment::class.java
        override fun getNewInstance() = UserSelectFragment.newInstance()
    }

    object UserAuth: Screen() {
        override fun fragment(): Class<out Fragment> = UserAuthFragment::class.java
        override fun getNewInstance() = UserAuthFragment.newInstance()
    }

}

sealed class ScreenModal : Screen() {

    abstract fun getTag(): String

    object Filter : ScreenModal() {
        override fun getTag() = TaskFilterDialogFragment.TAG
        override fun fragment(): Class<out Fragment> = TaskFilterDialogFragment::class.java
        override fun getNewInstance() = TaskFilterDialogFragment.newInstance()
    }

    object Sorting : ScreenModal() {
        override fun getTag() = TaskSortingDialogFragment.TAG
        override fun fragment(): Class<out Fragment> = TaskSortingDialogFragment::class.java
        override fun getNewInstance() = TaskSortingDialogFragment.newInstance()
    }

    object Importance : ScreenModal() {
        override fun getTag() = TaskImportanceDialogFragment.TAG
        override fun fragment(): Class<out Fragment> = TaskImportanceDialogFragment::class.java
        override fun getNewInstance() = TaskImportanceDialogFragment()
    }


}

