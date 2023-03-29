package com.example.todoapp.presentation.tasks

import androidx.core.view.GravityCompat
import androidx.lifecycle.LifecycleOwner
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTasksBinding
import com.example.todoapp.databinding.ViewSideNavHeaderBinding
import com.example.todoapp.presentation.tasks.adapter.TasksAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TasksViewController @AssistedInject constructor(
    @Assisted("tasksFragmentBinding") private val binding: FragmentTasksBinding,
    @Assisted("tasksLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("tasksViewModel") private val viewModel: TasksViewModel,
    @Assisted("tasksAdapter") private val tasksAdapter: TasksAdapter
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("tasksFragmentBinding") binding: FragmentTasksBinding,
            @Assisted("tasksLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("tasksViewModel") viewModel: TasksViewModel,
            @Assisted("tasksAdapter") tasksAdapter: TasksAdapter
        ): TasksViewController
    }

    fun setupViews() {
        binding.rvList.rvListAndTasks.apply {
            adapter = this@TasksViewController.tasksAdapter
        }

        viewModel.observeTasks(lifecycleOwner) { state ->
            state.apply(
                binding.rvList.rvListAndTasks,
                binding.tvEmptyList,
                binding.tvLoadingList
            )

        }

        val viewHeader = binding.navView.getHeaderView(0)
        val bindingHeader = ViewSideNavHeaderBinding.bind(viewHeader)


        viewModel.observeFilterCompleted(lifecycleOwner) { state ->
            state.apply(binding.cbHideCompleted)
        }

        viewModel.observeUser(lifecycleOwner) { user ->
            bindingHeader.tvUserLogin.text = user.email
        }

        viewModel.observeCntTasksImportantNotCompleted(lifecycleOwner) { cntTasks ->
            binding.twCountTaskImportantValue.text = cntTasks.toString()
        }

        viewModel.observeCntTasksNotCompleted(lifecycleOwner) { cntTasks ->
            binding.tvCountTaskValue.text = cntTasks.toString()
        }

        viewModel.observeCntTasksCompleted(lifecycleOwner) { cntTasks ->
            binding.twCountTaskCompletedValue.text = cntTasks.toString()
        }

        binding.fabAddTask.setOnClickListener {
            viewModel.showDetails()
        }

        binding.cbHideCompleted.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveSettingHideCompleted(isChecked)
        }

        binding.ibMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_sign_out -> {
                    viewModel.signOut()
                    true
                }
                else -> false
            }
        }

        binding.btmAppBarFab.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.btm_search -> {
                    viewModel.showDetails()
                    true
                }
                R.id.btm_sort -> {
                    viewModel.showDetails()
                    true
                }
                R.id.btm_filter -> {
                    viewModel.showDetails()
                    true
                }
                else -> false
            }
        }

    }

}
