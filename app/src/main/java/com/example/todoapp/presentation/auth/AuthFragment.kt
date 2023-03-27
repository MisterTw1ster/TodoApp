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
import com.example.todoapp.databinding.FragmentAuthBinding
import com.example.todoapp.di.authfragment.AuthFragmentComponent
import javax.inject.Inject

class AuthFragment : Fragment() {

    private lateinit var authFragmentComponent: AuthFragmentComponent

    @Inject
    lateinit var authViewControllerFactory: AuthViewController.Factory

    private var authViewController: AuthViewController? = null
    @Inject
    lateinit var authViewModelFactory: AuthViewModelFactory.Factory

    private val viewModel: AuthViewModel by viewModels {
        authViewModelFactory.create(
            AuthCommunication.Base(),
            (requireActivity().applicationContext as App).provideNavigationCommunication()
        )
    }

    private var binding: FragmentAuthBinding? = null

    override fun onAttach(context: Context) {
        authFragmentComponent =
            context.appComponent.authFragmentComponent().create()
        authFragmentComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewController =
            authViewControllerFactory.create(
                binding!!,
                viewLifecycleOwner,
                viewModel,
            )
        authViewController?.apply {
            setupViews()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        authViewController = null
        binding = null
    }

}