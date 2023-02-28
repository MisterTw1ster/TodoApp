package com.example.todoapp.presentation.tasks.models

import android.widget.TextView

sealed class StateTitleUI {

    abstract fun apply(textView: TextView)

    class Success(private val size: Int): StateTitleUI() {
        override fun apply(textView: TextView) {
            textView.text = "Выполнено - $size"
        }
    }

    object Empty: StateTitleUI() {
        override fun apply(textView: TextView) {
            textView.text = "Нет выполненных задач"
        }
    }

    object Loading: StateTitleUI() {
        override fun apply(textView: TextView) {
            textView.text = "Подождите"
        }
    }

}