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

    abstract class Abstract(
        private val visibility: Int,
        private val deadlineText: String
        ) : StateDeadlineUI() {
        override fun apply(textView: TextView, compoundButton: CompoundButton) {
            textView.visibility = visibility
            textView.text = deadlineText
        }
    }

    class On(deadlineText: String) : Abstract(View.VISIBLE, deadlineText)
    class Off : Abstract(View.GONE, DEFAULT_TEXT_DEADLINE)

    companion object {
        private const val DEFAULT_TEXT_DEADLINE = "Выбрать дату"
    }
}
