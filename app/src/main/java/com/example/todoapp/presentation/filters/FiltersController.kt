package com.example.todoapp.presentation.filters

import androidx.lifecycle.LifecycleOwner
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentFiltersBinding
import com.example.todoapp.presentation.common.ManageResources
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FiltersController @AssistedInject constructor(
    @Assisted("filtersFragment") private val fragment: FiltersFragment,
    @Assisted("filtersFragmentBinding") private val binding: FragmentFiltersBinding,
    @Assisted("filtersLifecycleOwner") private val lifecycleOwner: LifecycleOwner,
    @Assisted("filtersViewModel") private val viewModel: FiltersViewModel,
    private val resources: ManageResources
) {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("filtersFragment") fragment: FiltersFragment,
            @Assisted("filtersFragmentBinding") binding: FragmentFiltersBinding,
            @Assisted("filtersLifecycleOwner") lifecycleOwner: LifecycleOwner,
            @Assisted("filtersViewModel") viewModel: FiltersViewModel
        ): FiltersController
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
        binding.btnSaveTask.setOnClickListener {
            viewModel.saveFilters()
        }
    }

    private fun setupCloseScreen() {
        viewModel.observeCloseScreen(lifecycleOwner) { close ->
            if (close) fragment.dismiss()
        }
    }
}
