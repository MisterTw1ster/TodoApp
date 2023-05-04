package com.example.details.importancebottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.details.databinding.FragmentImportanceBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ImportanceModalBottomSheetFragment : BottomSheetDialogFragment() {

    var binding: FragmentImportanceBottomSheetBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImportanceBottomSheetBinding.inflate(inflater, container, false)
        return binding?.root ?: throw IllegalArgumentException("Layout not found: $inflater")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding!!) {
            llLowGroup.setOnClickListener { setResult("low") }
            llBasicGroup.setOnClickListener { setResult("basic") }
            llImportantGroup.setOnClickListener { setResult("important") }
        }
    }

    private fun setResult(result: String) {
        setFragmentResult("importanceKey", bundleOf("bundleKey" to result))
        dismiss()
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}