package com.example.components.presentation.navigation

import androidx.fragment.app.Fragment
import com.example.details.TaskDetailsFragment
import com.example.details.importancebottomsheet.ImportanceModalBottomSheetFragment
import com.example.feature_list.ListFragment
import com.example.feature_tasks_filters.TasksFiltersFragment
import com.example.feature_tasks_sorting.TasksSortingFragment
import com.example.feature_user_auth.UserAuthFragment
import com.example.feature_user_select.UserSelectFragment

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

    object Filters : ScreenModal() {
        override fun getTag() = TasksFiltersFragment.TAG
        override fun fragment(): Class<out Fragment> = TasksFiltersFragment::class.java
        override fun getNewInstance() = TasksFiltersFragment.newInstance()
    }

    object Sorting : ScreenModal() {
        override fun getTag() = TasksSortingFragment.TAG
        override fun fragment(): Class<out Fragment> = TasksSortingFragment::class.java
        override fun getNewInstance() = TasksSortingFragment.newInstance()
    }

    object Importance : ScreenModal() {
        override fun getTag() = ImportanceModalBottomSheetFragment.TAG
        override fun fragment(): Class<out Fragment> = ImportanceModalBottomSheetFragment::class.java
        override fun getNewInstance() = ImportanceModalBottomSheetFragment()
    }
}

