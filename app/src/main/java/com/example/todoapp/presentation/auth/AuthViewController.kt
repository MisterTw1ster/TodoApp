package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import com.example.todoapp.databinding.FragmentAuthBinding
import com.example.todoapp.presentation.common.Navigation
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AuthViewController @AssistedInject constructor(
    @Assisted("authFragment") private val fragment: AuthFragment,
    @Assisted("authFragmentBinding") private val binding: FragmentAuthBinding,
    @Assisted("authLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("authViewModel") private val viewModel: AuthViewModel,
    private val navigation: Navigation
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("authFragment") fragment: AuthFragment,
            @Assisted("authFragmentBinding") binding: FragmentAuthBinding,
            @Assisted("authLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("authViewModel") viewModel: AuthViewModel
        ): AuthViewController
    }

    fun setupViews() {

        viewModel.observeUsers(lifecycleOwner) { state ->

        }

        viewModel.observeError(lifecycleOwner) { state ->

        }

        binding.btnSingIn.setOnClickListener {
            navigation.tasksFragment(fragment)
        }

        binding.btnSingUp.setOnClickListener {
            navigation.tasksFragment(fragment)
        }

    }
}