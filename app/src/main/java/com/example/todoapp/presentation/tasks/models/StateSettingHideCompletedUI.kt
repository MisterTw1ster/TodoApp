package com.example.todoapp.presentation.tasks.models

import android.widget.CompoundButton

sealed class StateSettingHideCompletedUI {
    abstract fun apply(compoundButton: CompoundButton)

    class Initial(
        private val enabled: Boolean,
        private val stateSettingHideCompletedUI: StateSettingHideCompletedUI
    ) : StateSettingHideCompletedUI() {
        override fun apply(compoundButton: CompoundButton) {
            compoundButton.isChecked = enabled
            stateSettingHideCompletedUI.apply(compoundButton)
        }
    }

    abstract class Abstract() : StateSettingHideCompletedUI() {
        override fun apply(compoundButton: CompoundButton) { }
    }

    object On : Abstract()
    object Off : Abstract()

}
