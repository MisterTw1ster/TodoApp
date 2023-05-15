package com.example.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    abstract val inflater: (LayoutInflater, ViewGroup?, Boolean) -> T
    protected val binding: T get() = _binding!!
    private var _binding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel(savedInstanceState)
        initController()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    open fun initController() {
        // Override in subclasses
    }

    open fun initViewModel(savedInstanceState: Bundle?) {
        // Override in subclasses
    }

}