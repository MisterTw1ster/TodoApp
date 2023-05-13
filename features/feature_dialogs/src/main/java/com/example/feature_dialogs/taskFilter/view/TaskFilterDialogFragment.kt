package com.example.feature_dialogs.taskFilter.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.core.base.BaseBottomSheetFragment
import com.example.feature_dialogs.taskFilter.viewModel.TaskFilterComponentViewModel
import com.example.feature_dialogs.taskFilter.viewModel.TaskFilterViewModel
import com.example.feature_dialogs.taskFilter.viewModel.TaskFilterViewModelFactory
import javax.inject.Inject
import com.example.feature_dialogs.databinding.TasksFilterDialogFragmentBinding as Binding

class TaskFilterDialogFragment : BaseBottomSheetFragment<Binding>() {

    @Inject
    lateinit var viewControllerFactory: TaskFilterViewController.Factory
    private var viewController: TaskFilterViewController? = null

    @Inject
    lateinit var viewModelFactory: TaskFilterViewModelFactory
    private val viewModel: TaskFilterViewModel by viewModels { viewModelFactory }

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding =
        Binding::inflate

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<TaskFilterComponentViewModel>()
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
        const val DIALOG_TITLE = "Фильтр"
        const val TAG = "DialogFilter"
        fun newInstance() = TaskFilterDialogFragment()
    }
}