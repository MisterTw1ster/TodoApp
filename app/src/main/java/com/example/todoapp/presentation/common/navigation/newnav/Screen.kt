package com.example.todoapp.presentation.common.navigation.newnav

import androidx.fragment.app.Fragment
import com.example.todoapp.presentation.auth.AuthFragment
import com.example.todoapp.presentation.auth.SelectUserFragment
import com.example.todoapp.presentation.details.DetailsFragment
import com.example.todoapp.presentation.details.importancebottomsheet.ImportanceModalBottomSheetFragment
import com.example.todoapp.presentation.tasks.TasksFragment
import com.example.todoapp.presentation.filters.FiltersFragment

sealed class Screen {

    abstract fun fragment(): Class<out Fragment>
    abstract fun getNewInstance(): Fragment

    object SelectUser : Screen() {
        override fun fragment(): Class<out Fragment> = SelectUserFragment::class.java
        override fun getNewInstance() = SelectUserFragment()
    }

    object Auth : Screen() {
        override fun fragment(): Class<out Fragment> = AuthFragment::class.java
        override fun getNewInstance() = AuthFragment()
    }

    class Tasks(private val userId: String) : Screen() {
        override fun fragment(): Class<out Fragment> = TasksFragment::class.java
        override fun getNewInstance() = TasksFragment.newInstance(userId)
    }

    class Details(
        private val taskId: Long,
        private val userId: String,
    ) : Screen() {
        override fun fragment(): Class<out Fragment> = DetailsFragment::class.java
        override fun getNewInstance() = DetailsFragment.newInstance(taskId, userId)
    }

}

sealed class ScreenModal : Screen() {

    abstract fun getTag(): String

    object Filters : ScreenModal() {
        override fun getTag() = FiltersFragment.TAG
        override fun fragment(): Class<out Fragment> = FiltersFragment::class.java
        override fun getNewInstance() = FiltersFragment()
    }

    object Importance : ScreenModal() {
        override fun getTag() = ImportanceModalBottomSheetFragment.TAG
        override fun fragment(): Class<out Fragment> = ImportanceModalBottomSheetFragment::class.java
        override fun getNewInstance() = ImportanceModalBottomSheetFragment()
    }
}
