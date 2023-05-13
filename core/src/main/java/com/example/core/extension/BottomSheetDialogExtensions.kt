package com.example.core.extension

import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

val BottomSheetDialogFragment.behavior: BottomSheetBehavior<View>?
    get() = dialog?.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        ?.let { bottomSheet -> BottomSheetBehavior.from(bottomSheet) }

fun BottomSheetDialogFragment.setSkipCollapsed() {
    behavior?.apply {
        peekHeight = 0
        skipCollapsed = true
        state = BottomSheetBehavior.STATE_EXPANDED
    }
}

fun BottomSheetDialogFragment.setFullScreen() {
    (view?.parent as? FrameLayout)?.apply {
        layoutParams?.height = CoordinatorLayout.LayoutParams.MATCH_PARENT
        requestLayout()
    }
}

fun BottomSheetDialogFragment.blockContentScroll(recyclerView: RecyclerView) {
//    recyclerView.addOnScrollListenerAdapter(
//        onScrolled = { _, _, dy ->
//            if (dy != 0) behavior?.isDraggable = false
//        },
//        onScrollStateChanged = { _, newState ->
//            if (newState == RecyclerView.SCROLL_STATE_IDLE) behavior?.isDraggable = true
//        }
//    )
}