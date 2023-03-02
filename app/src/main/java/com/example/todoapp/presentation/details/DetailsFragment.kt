package com.example.todoapp.presentation.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.appComponent
import com.example.todoapp.databinding.FragmentDetailsBinding
import com.example.todoapp.di.detailsFragment.DetailsFragmentComponent
import com.google.android.material.datepicker.MaterialDatePicker
import javax.inject.Inject

class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    private val args by navArgs<DetailsFragmentArgs>()

    private lateinit var taskDetailsComponent: DetailsFragmentComponent

    @Inject
    lateinit var detailsControllerFactory: DetailsController.Factory
    private var detailsController: DetailsController? = null

    @Inject
    lateinit var communicationDetails: CommunicationDetails.Base.Factory

    @Inject
    lateinit var detailsViewModelFactory: DetailsViewModelFactory.Factory
    private val viewModel: DetailsViewModel by viewModels {
        detailsViewModelFactory.create(args.taskID, communicationDetails.create())
    }

    override fun onAttach(context: Context) {
        taskDetailsComponent =
            context.appComponent.detailsFragmentComponent().create()
        taskDetailsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner,
            listOf("low", "basic", "important")
        ).apply {
            setDropDownViewResource(android.R.layout.simple_list_item_1)
        }

        val datePicker: MaterialDatePicker<Long> = MaterialDatePicker
            .Builder
            .datePicker()
            .setTitleText("Select date of deadline")
            .build()

        detailsController =
            detailsControllerFactory.create(
                this,
                binding,
                viewLifecycleOwner,
                viewModel,
                args,
                spinnerAdapter,
                datePicker,
            )
        detailsController?.apply {
            setupViews()
        }
    }

}