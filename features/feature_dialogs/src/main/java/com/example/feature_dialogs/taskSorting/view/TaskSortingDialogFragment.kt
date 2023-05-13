package com.example.feature_dialogs.taskSorting.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.core.base.BaseBottomSheetFragment
import com.example.feature_dialogs.databinding.TaskSortingDialogFragmentBinding
import com.example.feature_dialogs.taskSorting.viewModel.TaskSortingComponentViewModel
import com.example.feature_dialogs.taskSorting.viewModel.TaskSortingViewModel
import com.example.feature_dialogs.taskSorting.viewModel.TaskSortingViewModelFactory
import javax.inject.Inject
import com.example.feature_dialogs.databinding.TaskSortingDialogFragmentBinding as Binding

class TaskSortingDialogFragment : BaseBottomSheetFragment<Binding>() {

    @Inject
    lateinit var viewControllerFactory: TaskSortingViewController.Factory
    private var viewController: TaskSortingViewController? = null

    @Inject
    lateinit var viewModelFactory: TaskSortingViewModelFactory
    private val viewModel: TaskSortingViewModel by viewModels { viewModelFactory }

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> TaskSortingDialogFragmentBinding =
        TaskSortingDialogFragmentBinding::inflate

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<TaskSortingComponentViewModel>()
            .component.inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewController = null
    }

    override fun initController() {
        viewController =
            viewControllerFactory.create(
                this,
                binding,
                viewLifecycleOwner,
                viewModel
            )
        viewController?.apply {
            setupViews()
        }
    }

    override fun initViewModel(savedInstanceState: Bundle?) {
        viewModel.init(savedInstanceState == null)
    }

    companion object {
        const val DIALOG_TITLE = "Сортировка"
        const val TAG = "ModalSorting"
        fun newInstance() = TaskSortingDialogFragment()
    }

}