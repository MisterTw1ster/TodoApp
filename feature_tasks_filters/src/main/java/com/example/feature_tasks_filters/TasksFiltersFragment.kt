package com.example.feature_tasks_filters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.feature_tasks_filters.databinding.FragmentTasksFiltersBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class TasksFiltersFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewControllerFactory: TasksFiltersViewController.Factory
    private var viewController: TasksFiltersViewController? = null

    @Inject
    lateinit var viewModelFactory: TasksFiltersViewModelFactory
    private val viewModel: TasksFiltersViewModel by viewModels { viewModelFactory }

    var binding: FragmentTasksFiltersBinding? = null

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<TasksFiltersComponentViewModel>()
            .tasksFiltersComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksFiltersBinding.inflate(inflater, container, false)
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(savedInstanceState == null)
        viewController =
            viewControllerFactory.create(
                this,
                binding!!,
                viewLifecycleOwner,
                viewModel
            )
        viewController?.apply {
            setupViews()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewController = null
        binding = null
    }

    companion object {
        const val TAG = "ModalFilters"
        fun newInstance() = TasksFiltersFragment()
    }
}