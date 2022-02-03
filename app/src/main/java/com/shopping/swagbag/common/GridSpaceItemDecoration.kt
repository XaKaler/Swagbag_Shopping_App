package com.shopping.swagbag.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpaceItemDecoration(var space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        val column: Int = position % 2

        outRect.left = column * space / 2; // column * ((1f / spanCount) * spacing)
        outRect.right = space - (column + 1) * space / 2;
        outRect.bottom = space * 2
        outRect.top = space * 2

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }
}