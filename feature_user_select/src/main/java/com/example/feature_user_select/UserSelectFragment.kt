package com.example.feature_user_select

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.feature_user_select.databinding.FragmentUserSelectBinding
import javax.inject.Inject


class UserSelectFragment : Fragment() {

    @Inject
    lateinit var viewControllerFactory: UserSelectViewController.Factory
    private var viewController: UserSelectViewController? = null

    @Inject
    lateinit var viewModelFactory: UserSelectViewModelFactory
    private val viewModel: UserSelectViewModel by viewModels { viewModelFactory }

    private var binding: FragmentUserSelectBinding? = null

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<UserSelectComponentViewModel>()
            .userSelectComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserSelectBinding.inflate(inflater, container, false)
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(savedInstanceState == null)
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
        fun newInstance() = UserSelectFragment()
    }

}
