package com.shopping.swagbag.common

import android.view.View

interface RecycleItemClickListener {
    fun onSingleItemClickListener(position: Int)
}

interface HomeCategoryRecycleItemClickListener {
    fun onHomeCategorySingleItemClickListener(position: Int)
}

interface RecycleItemClick{
    fun onItemClick(name: String, position: Int)

    fun onItemClickWithView( position: Int, view: View)


}



