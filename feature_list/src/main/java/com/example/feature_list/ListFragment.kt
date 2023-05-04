package com.example.feature_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.feature_list.databinding.FragmentListBinding
import com.example.feature_list.rvlist.ItemTouchHelperCallback
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var viewControllerFactory: ListViewController.Factory
    private var viewController: ListViewController? = null

    @Inject
    lateinit var viewModelFactory: ListViewModelFactory.Factory
    private val viewModel: ListViewModel by viewModels {
        viewModelFactory.create(userId)
    }

    @Inject
    lateinit var itemTouchHelperCallbackFactory: ItemTouchHelperCallback.Factory
    private var itemTouchHelperCallback: ItemTouchHelperCallback? = null

    private var binding: FragmentListBinding? = null
    private val userId: String by lazy { arguments?.getString(ID_USER)!! }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ListComponentViewModel>()
            .listComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(savedInstanceState == null)
        itemTouchHelperCallback =
            itemTouchHelperCallbackFactory.create(viewModel, binding!!.clListGroup)
        viewController =
            viewControllerFactory.create(
                binding!!,
                viewLifecycleOwner,
                viewModel,
                itemTouchHelperCallback!!
            )
        viewController?.apply {
            setupViews()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        viewController = null
        itemTouchHelperCallback = null
    }

    companion object {

        private const val ID_USER = "user_id"

        fun newInstance(userId: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_USER, userId)
                }
            }
    }
}