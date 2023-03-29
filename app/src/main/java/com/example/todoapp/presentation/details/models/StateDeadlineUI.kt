package com.example.todoapp.presentation.details.models

import android.view.View
import android.widget.CompoundButton
import android.widget.TextView

sealed class StateDeadlineUI {
    abstract fun apply(textView: TextView, compoundButton: CompoundButton)

    class Initial(
        private val enabled: Boolean,
        private val stateDeadlineUI: StateDeadlineUI
    ) : StateDeadlineUI() {
        override fun apply(textView: TextView, compoundButton: CompoundButton) {
            compoundButton.isChecked = enabled
            stateDeadlineUI.apply(textView, compoundButton)
        }
    }

    class On(private val deadlineText: String) : StateDeadlineUI() {
        override fun apply(textView: TextView, compoundButton: CompoundButton) {
            textView.visibility = View.VISIBLE
            textView.text = deadlineText
        }
    }

    object Off : StateDeadlineUI() {
        override fun apply(textView: TextView, compoundButton: CompoundButton) {
            textView.visibility = View.GONE
        }

    }
}
