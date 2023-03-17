//package com.example.todoapp.presentation.auth.models
//
//import android.view.View
//import android.view.ViewGroup
//
//sealed class StateScreenUI {
//
//    abstract fun apply(view1: ViewGroup, view2: ViewGroup)
//
//    object SelectUser: StateScreenUI() {
//        override fun apply(view1: ViewGroup, view2: ViewGroup) {
//            view1.visibility = View.VISIBLE
//            view2.visibility = View.GONE
//        }
//    }
//
//    object AddUser: StateScreenUI() {
//        override fun apply(view1: ViewGroup, view2: ViewGroup) {
//            view2.visibility = View.VISIBLE
//            view1.visibility = View.GONE
//        }
//    }
//}
