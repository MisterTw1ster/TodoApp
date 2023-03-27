package com.example.todoapp.presentation.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todoapp.App
import com.example.todoapp.appComponent
import com.example.todoapp.databinding.FragmentSelectUserBinding
import com.example.todoapp.di.authfragment.SelectUserFragmentComponent
import javax.inject.Inject

class SelectUserFragment : Fragment() {

    private lateinit var selectUserFragmentComponent: SelectUserFragmentComponent

    @Inject
    lateinit var selectUserViewControllerFactory: SelectUserViewController.Factory
    private var selectUserViewController: SelectUserViewController? = null

    @Inject
    lateinit var selectUserViewModelFactory: SelectUserViewModelFactory.Factory

    private val viewModel: SelectUserViewModel by viewModels {
        selectUserViewModelFactory.create(
            SelectUserCommunication.Base(),
            (requireActivity().applicationContext as App).provideNavigationCommunication()
        )
    }

    private var binding: FragmentSelectUserBinding? = null

    override fun onAttach(context: Context) {
        selectUserFragmentComponent =
            context.appComponent.selectUserFragmentComponent().create()
        selectUserFragmentComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectUserBinding.inflate(inflater, container, false)
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(savedInstanceState == null)
        selectUserViewController =
            selectUserViewControllerFactory.create(
                binding!!,
                viewLifecycleOwner,
                viewModel,
            )
        selectUserViewController?.apply {
            setupViews()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        selectUserViewController = null
        binding = null
    }

}
