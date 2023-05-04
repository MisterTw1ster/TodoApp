package com.example.feature_list.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.feature_list.R
import com.google.android.material.snackbar.BaseTransientBottomBar

class DeleteItemSnackbar(
    parent: ViewGroup,
    content: DeleteItemSnackbarView
) : BaseTransientBottomBar<DeleteItemSnackbar>(parent, content, content) {

    init {
        getView().setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {

        fun make(view: View): DeleteItemSnackbar {
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            val customView = LayoutInflater.from(view.context).inflate(
                R.layout.layout_snackbar_delete_item,
                parent,
                false
            ) as DeleteItemSnackbarView

            return DeleteItemSnackbar(parent, customView)
        }

        fun DeleteItemSnackbar.setAction(listener: View.OnClickListener) {
            getView().findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
                listener.onClick(it)
                dismiss()
            }
        }

    }

}

