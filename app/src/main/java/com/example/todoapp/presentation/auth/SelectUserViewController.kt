package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import com.example.todoapp.databinding.FragmentSelectUserBinding
import com.example.todoapp.presentation.auth.adapter.UserListAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SelectUserViewController @AssistedInject constructor(
    @Assisted("selectUserFragment") private val fragment: SelectUserFragment,
    @Assisted("selectUserFragmentBinding") private val binding: FragmentSelectUserBinding,
    @Assisted("selectUserLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("selectUserViewModel") private val viewModel: SelectUserViewModel,
    private val usersAdapter: UserListAdapter
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("selectUserFragment") fragment: SelectUserFragment,
            @Assisted("selectUserFragmentBinding") binding: FragmentSelectUserBinding,
            @Assisted("selectUserLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("selectUserViewModel") viewModel: SelectUserViewModel
        ): SelectUserViewController
    }

    fun setupViews() {

        binding.rvLocalUsers.apply {
            adapter = this@SelectUserViewController.usersAdapter
        }

        usersAdapter.setOnItemClickListener { user ->
            viewModel.selectUser(user)
        }

        viewModel.observeUsers(lifecycleOwner) { users ->
            usersAdapter.submitList(users)
        }

//        viewModel.observeStateScreen(lifecycleOwner) { state ->
//            state.apply(binding.llSelectUser, binding.llNewUser)
//        }
//
//        viewModel.observeError(lifecycleOwner) { error ->
//            binding.tvError.text = error
//        }

//        viewModel.observeNavigate(lifecycleOwner) { navigation ->
//            navigation.navigate(fragment)
//        }

        binding.twAddUser.setOnClickListener {
            viewModel.showAddUser()
        }

//        binding.btnSingIn.setOnClickListener {
//            viewModel.signInWithEmail()
//        }
//
//        binding.btnSingUp.setOnClickListener {
//            viewModel.signUpWithEmail()
//        }
//
//        binding.etLogin.doAfterTextChanged {
//            if (binding.etLogin.hasFocus()) {
//                viewModel.setLogin(it.toString())
//            }
//        }

//        binding.etPassword.doAfterTextChanged {
//            if (binding.etPassword.hasFocus()) {
//                viewModel.setPassword(it.toString())
//            }
//        }

    }
}