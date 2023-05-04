package com.example.feature_user_auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.feature_user_auth.databinding.FragmentUserAuthBinding
import javax.inject.Inject

class UserAuthFragment : Fragment() {

    @Inject
    lateinit var viewControllerFactory: UserAuthViewController.Factory
    private var viewController: UserAuthViewController? = null

    @Inject
    lateinit var viewModelFactory: UserAuthViewModelFactory
    private val viewModel: UserAuthViewModel by viewModels { viewModelFactory }

    private var binding: FragmentUserAuthBinding? = null

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<UserAuthComponentViewModel>()
            .userAuthComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserAuthBinding.inflate(inflater, container, false)
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewController =
            viewControllerFactory.create(
                binding!!,
                viewLifecycleOwner,
                viewModel,
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
        fun newInstance() = UserAuthFragment()
    }

}