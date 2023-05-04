package com.example.details.models

import android.view.View
import android.widget.ImageView
import android.widget.TextView

sealed class StateDeadlineUI {
    abstract fun apply(textView: TextView, imageView: ImageView)

    class Initial(
        private val enabled: Boolean,
        private val stateDeadlineUI: StateDeadlineUI
    ) : StateDeadlineUI() {
        override fun apply(textView: TextView, imageView: ImageView) {
            stateDeadlineUI.apply(textView, imageView)
        }
    }

    class Success(private val deadlineText: String) : StateDeadlineUI() {
        override fun apply(textView: TextView, imageView: ImageView) {
            imageView.visibility = View.VISIBLE
            textView.text = deadlineText
        }
    }

    object Empty : StateDeadlineUI() {
        override fun apply(textView: TextView, imageView: ImageView) {
            imageView.visibility = View.GONE
            textView.text = "Выбрать дату"
        }

    }
}
