package com.example.user_auth.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.core.base.BaseFragment
import com.example.user_auth.viewModel.UserAuthComponentViewModel
import com.example.user_auth.viewModel.UserAuthViewModel
import com.example.user_auth.viewModel.UserAuthViewModelFactory
import javax.inject.Inject
import com.example.user_auth.databinding.FragmentUserAuthBinding as Binding

class UserAuthFragment : BaseFragment<Binding>() {

    @Inject
    lateinit var viewControllerFactory: UserAuthViewController.Factory
    private var viewController: UserAuthViewController? = null

    @Inject
    lateinit var viewModelFactory: UserAuthViewModelFactory
    private val viewModel: UserAuthViewModel by viewModels { viewModelFactory }

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding =
        Binding::inflate

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<UserAuthComponentViewModel>()
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
                binding,
                viewLifecycleOwner,
                viewModel,
            )
        viewController?.apply {
            setupViews()
        }
    }

    override fun initViewModel(savedInstanceState: Bundle?) {

    }

    companion object {
        fun newInstance() = UserAuthFragment()
    }

}