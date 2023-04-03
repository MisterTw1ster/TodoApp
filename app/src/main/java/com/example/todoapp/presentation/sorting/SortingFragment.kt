package com.example.todoapp.presentation.sorting

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todoapp.appComponent
import com.example.todoapp.databinding.FragmentSortingBinding
import com.example.todoapp.di.sorting.SortingFragmentComponent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class SortingFragment : BottomSheetDialogFragment() {

    var binding: FragmentSortingBinding? = null
    private lateinit var sortingComponent: SortingFragmentComponent

    @Inject
    lateinit var sortingControllerFactory: SortingController.Factory
    private var sortingController: SortingController? = null


    @Inject
    lateinit var sortingViewModelFactory:SortingViewModelFactory.Factory
    private val viewModel:SortingViewModel by viewModels {
        sortingViewModelFactory.create(CommunicationSorting.BaseSorting())
    }

    override fun onAttach(context: Context) {
        sortingComponent =
            context.appComponent.sortingFragmentComponent().create()
        sortingComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortingBinding.inflate(inflater, container, false)
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(savedInstanceState == null)
        sortingController =
            sortingControllerFactory.create(
                this,
                binding!!,
                viewLifecycleOwner,
                viewModel
            )
        sortingController?.apply {
            setupViews()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sortingController = null
        binding = null
    }

    companion object {
        const val TAG = "ModalSorting"
    }
}