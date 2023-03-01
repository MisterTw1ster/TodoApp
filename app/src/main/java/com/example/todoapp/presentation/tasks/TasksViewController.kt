package com.example.todoapp.presentation.tasks

import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTasksBinding
import com.example.todoapp.presentation.tasks.adapter.TasksAdapter
import com.example.todoapp.presentation.tasks.models.StateTasksUI
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TasksViewController @AssistedInject constructor(
    @Assisted("tasksFragment") private val fragment: TasksFragment,
    @Assisted("tasksFragmentBinding") private val binding: FragmentTasksBinding,
    @Assisted("tasksLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("tasksViewModel") private val viewModel: TasksViewModel,
    @Assisted("tasksAdapter") private val tasksAdapter: TasksAdapter
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
            adapter = tasksAdapter
        }

        viewModel.observeTasks(lifecycleOwner) { state ->
            state.apply(
                binding.rvList.rvListAndTasks,
                binding.tvEmptyList,
                binding.tvLoadingList
            )

            viewModel.observeFilterCompleted(lifecycleOwner) { state ->
                state.apply(binding.cbHideCompleted)
            }

            viewModel.observeTitle(lifecycleOwner) { state ->
                state.apply(binding.tvTitle)
            }

            binding.fabAddTask.setOnClickListener {
                fragment.findNavController().navigate(
                    R.id.action_tasks_to_detailsFragment,
                )
            }

            binding.cbHideCompleted.setOnCheckedChangeListener { _, isChecked ->
                viewModel.saveSettingHideCompleted(isChecked)
            }
        }

    }
}