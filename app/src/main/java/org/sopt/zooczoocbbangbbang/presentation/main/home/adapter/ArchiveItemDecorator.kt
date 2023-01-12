package org.sopt.zooczoocbbangbbang.presentation.main.home.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.sopt.zooczoocbbangbbang.util.DisplayUtil

class ArchiveItemDecorator(
    private val context: Context,
    private val horizontalMargin: Int,
    private val verticalMargin: Int
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.right = DisplayUtil.dpToPx(context, horizontalMargin)
        outRect.bottom = DisplayUtil.dpToPx(context, verticalMargin)
    }
}
