package com.example.feature_list.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.feature_list.R
import com.google.android.material.snackbar.ContentViewCallback

class DeleteItemSnackbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes), ContentViewCallback {

    private val pb: ProgressBar

    init {
        View.inflate(context, R.layout.view_snackbar, this)
        pb = findViewById(R.id.progress_bar)
        clipToPadding = false
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        TODO("Not yet implemented")
    }

    override fun animateContentOut(delay: Int, duration: Int) {
        TODO("Not yet implemented")
    }

}