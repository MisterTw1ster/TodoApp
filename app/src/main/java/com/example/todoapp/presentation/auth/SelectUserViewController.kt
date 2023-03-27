package com.example.todoapp.presentation.auth

import androidx.lifecycle.LifecycleOwner
import com.example.todoapp.databinding.FragmentSelectUserBinding
import com.example.todoapp.presentation.auth.adapter.UserListAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SelectUserViewController @AssistedInject constructor(
    @Assisted("selectUserFragmentBinding") private val binding: FragmentSelectUserBinding,
    @Assisted("selectUserLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("selectUserViewModel") private val viewModel: SelectUserViewModel,
    private val usersAdapter: UserListAdapter
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("selectUserFragmentBinding") binding: FragmentSelectUserBinding,
            @Assisted("selectUserLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("selectUserViewModel") viewModel: SelectUserViewModel
        ): SelectUserViewController
    }

    fun setupViews() {
        setupUsersList()
        setupAddUser()
//        binding.rvLocalUsers.apply {
//            adapter = this@SelectUserViewController.usersAdapter
//        }
//
//        usersAdapter.setOnItemClickListener { user ->
//            viewModel.selectUser(user)
//        }
//
//        viewModel.observeUsers(lifecycleOwner) { users ->
//            usersAdapter.submitList(users)
//        }
//        binding.twAddUser.setOnClickListener {
//            viewModel.showAddUser()
//        }
    }

    private fun setupUsersList() {
        binding.rvLocalUsers.apply {
            adapter = this@SelectUserViewController.usersAdapter
        }

        usersAdapter.setOnItemClickListener { user ->
            viewModel.selectUser(user)
        }

        viewModel.observeUsers(lifecycleOwner) { users ->
            usersAdapter.submitList(users)
        }
    }

    private fun setupAddUser() {
        binding.twAddUser.setOnClickListener {
            viewModel.showAddUser()
        }
    }
}