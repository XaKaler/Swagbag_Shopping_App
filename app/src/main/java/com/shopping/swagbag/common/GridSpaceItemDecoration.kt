package com.shopping.swagbag.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpaceItemDecoration(var space: Int) : RecyclerView.ItemDecoration() {

    private val spanCount = 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        val column: Int = position % 2

        /*outRect.left = column
        outRect.right = space - (column + 1) * space / 2;
        outRect.bottom = space
        outRect.top = space

        if (position == 0) {
            outRect.top = 0
        } else {
            outRect.top = 0
        }*/

        outRect.left = column * space / spanCount; // column * ((1f / spanCount) * spacing)
        outRect.right = space - (column + 1) * space / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
        if (position >= spanCount) {
            outRect.top = space; // item top
        }
    }
}