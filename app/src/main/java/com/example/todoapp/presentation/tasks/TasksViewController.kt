package com.example.todoapp.presentation.tasks

import androidx.lifecycle.LifecycleOwner
import com.example.todoapp.databinding.FragmentTasksBinding
import com.example.todoapp.presentation.common.navigation.Navigation
import com.example.todoapp.presentation.tasks.adapter.TasksAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TasksViewController @AssistedInject constructor(
    @Assisted("tasksFragment") private val fragment: TasksFragment,
    @Assisted("tasksFragmentBinding") private val binding: FragmentTasksBinding,
    @Assisted("tasksLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("tasksViewModel") private val viewModel: TasksViewModel,
    @Assisted("tasksAdapter") private val tasksAdapter: TasksAdapter,
    private val navigation: Navigation
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("tasksFragment") fragment: TasksFragment,
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

        viewModel.observeFilterCompleted(lifecycleOwner) { state ->
            state.apply(binding.cbHideCompleted)
        }

        viewModel.observeTitle(lifecycleOwner) { state ->
            state.apply(binding.tvTitle)
        }

        viewModel.observeNavigate(lifecycleOwner) { navigation ->
            navigation.navigate(fragment)
        }

        binding.fabAddTask.setOnClickListener {
            navigation.newDetailsFragment(fragment)
        }

        binding.cbHideCompleted.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveSettingHideCompleted(isChecked)
        }

        binding.ibSignOut.setOnClickListener {
            viewModel.signOut()
        }

    }
}