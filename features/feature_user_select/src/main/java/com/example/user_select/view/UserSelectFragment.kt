package com.example.user_select.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.core.base.BaseFragment
import com.example.user_select.viewModel.UserSelectComponentViewModel
import com.example.user_select.viewModel.UserSelectViewModel
import com.example.user_select.viewModel.UserSelectViewModelFactory
import javax.inject.Inject
import com.example.user_select.databinding.FragmentUserSelectBinding as Binding


class UserSelectFragment : BaseFragment<Binding>() {

    @Inject
    lateinit var viewControllerFactory: UserSelectViewController.Factory
    private var viewController: UserSelectViewController? = null

    @Inject
    lateinit var viewModelFactory: UserSelectViewModelFactory
    private val viewModel: UserSelectViewModel by viewModels { viewModelFactory }

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding =
        Binding::inflate


    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<UserSelectComponentViewModel>()
            .userSelectComponent.inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewController = null
    }

    override fun initController() {
        viewController =
            viewControllerFactory.create(
                binding,
                viewLifecycleOwner,
                viewModel,
            )
        viewController?.apply {
            setupViews()
        }
    }

    override fun initViewModel(savedInstanceState: Bundle?) {
        viewModel.init(savedInstanceState == null)
    }
    companion object {
        fun newInstance() = UserSelectFragment()
    }

}
