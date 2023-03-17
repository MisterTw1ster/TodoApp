package com.example.todoapp.presentation.common.navigation.newnav

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.todoapp.R

interface NavigationStrategy {

    fun navigate(supportFragmentManager: FragmentManager, containerId: Int)

    abstract class Abstract(protected open val screen: Screen) : NavigationStrategy {

        override fun navigate(
            supportFragmentManager: FragmentManager,
            containerId: Int
        ) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.to_left_in, R.anim.to_left_out,
                    R.anim.to_right_in,R.anim.to_right_out
                )
                .executeTransaction(containerId)
                .commit()
        }

        protected abstract fun FragmentTransaction.executeTransaction(
            containerId: Int
        ): FragmentTransaction
    }

    data class Replace(override val screen: Screen) : Abstract(screen) {

        override fun FragmentTransaction.executeTransaction(containerId: Int) =
            replace(containerId, screen.getNewInstance())

    }

    data class Add(override val screen: Screen) : Abstract(screen) {

        override fun FragmentTransaction.executeTransaction(containerId: Int) =
            screen.fragment().let {
                add(containerId, screen.getNewInstance()).addToBackStack(it.simpleName)
            }
    }
}