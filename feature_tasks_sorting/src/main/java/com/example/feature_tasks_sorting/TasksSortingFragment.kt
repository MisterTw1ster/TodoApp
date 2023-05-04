package com.example.feature_tasks_sorting

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.feature_tasks_sorting.databinding.FragmentTasksSortingBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class TasksSortingFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewControllerFactory: TasksSortingViewController.Factory
    private var viewController: TasksSortingViewController? = null

    @Inject
    lateinit var viewModelFactory: TasksSortingViewModelFactory
    private val viewModel: TasksSortingViewModel by viewModels { viewModelFactory }

    private var binding: FragmentTasksSortingBinding? = null

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<TasksSortingComponentViewModel>()
            .tasksSortingComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksSortingBinding.inflate(inflater, container, false)
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
        const val TAG = "ModalSorting"
        fun newInstance() = TasksSortingFragment()
    }
}