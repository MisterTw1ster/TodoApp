package com.example.task_details.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.core.base.BaseFragment
import com.example.task_details.viewModel.DetailsViewModel
import com.example.task_details.viewModel.TaskDetailsComponentViewModel
import com.example.task_details.viewModel.TaskDetailsViewModelFactory
import javax.inject.Inject
import com.example.feature_task_details.databinding.FragmentDetailsBinding as Binding

class TaskDetailsFragment : BaseFragment<Binding>() {

    @Inject
    lateinit var viewControllerFactory: TaskDetailsViewController.Factory
    private var viewController: TaskDetailsViewController? = null

    @Inject
    lateinit var viewModelFactory: TaskDetailsViewModelFactory.Factory
    private val viewModel: DetailsViewModel by viewModels {
        viewModelFactory.create(taskId, userId)
    }

    private val taskId: Long by lazy { arguments?.getLong(ID_TASK)!! }
    private val userId: String by lazy { arguments?.getString(ID_USER)!! }

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding =
        Binding::inflate

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<TaskDetailsComponentViewModel>()
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

        private const val ID_TASK = "task_id"
        private const val ID_USER = "user_id"

        fun newInstance(userId: String, taskId: Long) =
            TaskDetailsFragment().apply {
                arguments = Bundle().apply {
                    putLong(ID_TASK, taskId)
                    putString(ID_USER, userId)
                }
            }
    }
}