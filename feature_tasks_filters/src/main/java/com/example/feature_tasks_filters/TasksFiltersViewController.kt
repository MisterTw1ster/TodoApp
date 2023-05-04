package com.example.feature_tasks_filters

import androidx.lifecycle.LifecycleOwner
import com.example.core.ManageResources
import com.example.feature_tasks_filters.databinding.FragmentTasksFiltersBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class TasksFiltersViewController @AssistedInject constructor(
    @Assisted("filtersFragment") private val fragment: TasksFiltersFragment,
    @Assisted("filtersFragmentBinding") private val binding: FragmentTasksFiltersBinding,
    @Assisted("filtersLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("filtersViewModel") private val viewModel: TasksFiltersViewModel,
    private val resources: ManageResources
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("filtersFragment") fragment: TasksFiltersFragment,
            @Assisted("filtersFragmentBinding") binding: FragmentTasksFiltersBinding,
            @Assisted("filtersLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("filtersViewModel") viewModel: TasksFiltersViewModel
        ): TasksFiltersViewController
    }

    fun setupViews() {
        setupHideCompleted()
        setupSaveFilters()
        setupCloseScreen()
    }

    private fun setupHideCompleted() {
        viewModel.observeHideCompleted(lifecycleOwner) { hide ->
            binding.tvHideCompletedFilter.text =
                if (hide) resources.string(R.string.title_hide_completed_false)
                else resources.string(R.string.title_hide_completed_true)
        }
        binding.llHideCompletedGroup.setOnClickListener {
            viewModel.setHideCompleted()
        }
    }

    private fun setupSaveFilters() {
        binding.btnSaveFilters.setOnClickListener {
            viewModel.saveFilters()
        }
    }

    private fun setupCloseScreen() {
        viewModel.observeCloseScreen(lifecycleOwner) { close ->
            if (close) fragment.dismiss()
        }
    }
}
