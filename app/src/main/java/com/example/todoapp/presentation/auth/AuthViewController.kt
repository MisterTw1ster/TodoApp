package com.example.todoapp.presentation.auth

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LifecycleOwner
import com.example.todoapp.databinding.FragmentAuthBinding
import com.example.todoapp.presentation.auth.adapter.UserListAdapter
import com.example.todoapp.presentation.common.navigation.Navigation
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AuthViewController @AssistedInject constructor(
    @Assisted("authFragment") private val fragment: AuthFragment,
    @Assisted("authFragmentBinding") private val binding: FragmentAuthBinding,
    @Assisted("authLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("authViewModel") private val viewModel: AuthViewModel,
    private val usersAdapter: UserListAdapter
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

        binding.rvLocalUsers.apply {
            adapter = this@AuthViewController.usersAdapter
        }

        usersAdapter.setOnItemClickListener { user ->
            viewModel.selectUser(user)
        }

        viewModel.observeUsers(lifecycleOwner) { users ->
            usersAdapter.submitList(users)
        }

        viewModel.observeError(lifecycleOwner) { error ->
            binding.tvError.text = error
        }

        viewModel.observeNavigate(lifecycleOwner) { navigation ->
            navigation.navigate(fragment)
        }

        binding.btnSingIn.setOnClickListener {
            viewModel.signInWithEmail()
        }

        binding.btnSingUp.setOnClickListener {
            viewModel.signUpWithEmail()
        }

        binding.etLogin.doAfterTextChanged {
            if (binding.etLogin.hasFocus()) {
                viewModel.setLogin(it.toString())
            }
        }

        binding.etPassword.doAfterTextChanged {
            if (binding.etPassword.hasFocus()) {
                viewModel.setPassword(it.toString())
            }
        }

    }
}