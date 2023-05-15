package com.example.task_list.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.core.base.BaseFragment
import com.example.task_list.rvList.ItemTouchHelperCallback
import com.example.task_list.viewModel.ListComponentViewModel
import com.example.task_list.viewModel.ListViewModel
import com.example.task_list.viewModel.ListViewModelFactory
import javax.inject.Inject
import com.example.feature_task_list.databinding.FragmentListBinding as Binding

class ListFragment : BaseFragment<Binding>() {

    @Inject
    lateinit var viewControllerFactory: ListViewController.Factory
    private var viewController: ListViewController? = null

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory.Factory
    private val viewModel: ListViewModel by viewModels {
        viewModelFactory.create(userId)
    }

    @Inject
    lateinit var itemTouchHelperCallbackFactory: ItemTouchHelperCallback.Factory
    private var itemTouchHelperCallback: ItemTouchHelperCallback? = null

    private val userId: String by lazy { arguments?.getString(ID_USER)!! }

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding =
        Binding::inflate

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ListComponentViewModel>()
            .component.inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewController = null
        itemTouchHelperCallback = null
    }

    override fun initController() {
        itemTouchHelperCallback =
            itemTouchHelperCallbackFactory.create(viewModel, binding.clListGroup)
        viewController =
            viewControllerFactory.create(
                binding,
                viewLifecycleOwner,
                viewModel,
                itemTouchHelperCallback!!
            )
        viewController?.apply {
            setupViews()
        }
    }

    override fun initViewModel(savedInstanceState: Bundle?) {
        viewModel.init(savedInstanceState == null)
    }

    companion object {

        private const val ID_USER = "user_id"

        fun newInstance(userId: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_USER, userId)
                }
            }
    }
}