package com.example.todoapp.presentation.filters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todoapp.appComponent
import com.example.todoapp.databinding.FragmentFiltersBinding
import com.example.todoapp.di.filters.FiltersFragmentComponent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class FiltersFragment : BottomSheetDialogFragment() {

    var binding: FragmentFiltersBinding? = null
    private lateinit var filtersComponent: FiltersFragmentComponent

    @Inject
    lateinit var filtersControllerFactory: FiltersController.Factory
    private var filtersController: FiltersController? = null


    @Inject
    lateinit var filtersViewModelFactory: FiltersViewModelFactory.Factory
    private val viewModel: FiltersViewModel by viewModels {
        filtersViewModelFactory.create(CommunicationFilters.BaseFilters())
    }

    override fun onAttach(context: Context) {
        filtersComponent =
            context.appComponent.filtersFragmentComponent().create()
        filtersComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFiltersBinding.inflate(inflater, container, false)
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(savedInstanceState == null)
        filtersController =
            filtersControllerFactory.create(
                this,
                binding!!,
                viewLifecycleOwner,
                viewModel
            )
        filtersController?.apply {
            setupViews()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        filtersController = null
        binding = null
    }

    companion object {
        const val TAG = "ModalFilters"
    }
}