package com.example.user_select.view

import androidx.lifecycle.LifecycleOwner
import com.example.user_select.viewModel.UserSelectViewModel
import com.example.user_select.databinding.FragmentUserSelectBinding
import com.example.user_select.rvusers.UsersItemAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class UserSelectViewController @AssistedInject constructor(
    @Assisted("selectUserFragmentBinding") private val binding: FragmentUserSelectBinding,
    @Assisted("selectUserLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("selectUserViewModel") private val viewModel: UserSelectViewModel,
    private var adapterUsers: UsersItemAdapter
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("selectUserFragmentBinding") binding: FragmentUserSelectBinding,
            @Assisted("selectUserLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("selectUserViewModel") viewModel: UserSelectViewModel
        ): UserSelectViewController
    }

    fun setupViews() {
        setupUsersList()
        setupAddUser()
    }

    private fun setupUsersList() {
        viewModel.observeUsers(lifecycleOwner) { users ->
            adapterUsers.submitList(users)
        }
        adapterUsers.onClick = { user -> viewModel.selectUser(user) }
        binding.rvLocalUsers.adapter = adapterUsers
    }

    private fun setupAddUser() {
        binding.twAddUser.setOnClickListener {
            viewModel.showAddUser()
        }
    }
}