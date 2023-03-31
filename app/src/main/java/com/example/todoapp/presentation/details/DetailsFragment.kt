package com.example.todoapp.presentation.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todoapp.App
import com.example.todoapp.appComponent
import com.example.todoapp.databinding.FragmentDetailsBinding
import com.example.todoapp.di.detailsfragment.DetailsFragmentComponent
import javax.inject.Inject

class DetailsFragment : Fragment() {

    private lateinit var taskDetailsComponent: DetailsFragmentComponent
    private val taskId: Long by lazy { arguments?.getLong(ID_TASK)!! }
    private val userId: String by lazy { arguments?.getString(ID_USER)!! }

    @Inject
    lateinit var detailsControllerFactory: DetailsController.Factory
    private var detailsController: DetailsController? = null

    @Inject
    lateinit var communicationDetails: CommunicationDetails.Base.Factory

    @Inject
    lateinit var detailsViewModelFactory: DetailsViewModelFactory.Factory
    private val viewModel: DetailsViewModel by viewModels {
        detailsViewModelFactory.create(
            taskId,
            userId,
            communicationDetails.create(),
            (requireActivity().applicationContext as App).provideNavigationCommunication()
        )
    }

    var binding: FragmentDetailsBinding? = null

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
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(savedInstanceState == null)
        detailsController =
            detailsControllerFactory.create(
                this,
                binding!!,
                viewLifecycleOwner,
                viewModel
            )
        detailsController?.apply {
            setupViews()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailsController = null
        binding = null
    }

    companion object {

        private const val ID_TASK = "task_id"
        private const val ID_USER = "user_id"

        @JvmStatic
        fun newInstance(taskId: Long, userId: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putLong(ID_TASK, taskId)
                    putString(ID_USER, userId)
                }
            }
    }
}