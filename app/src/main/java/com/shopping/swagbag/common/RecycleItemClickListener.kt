package com.shopping.swagbag.common

import android.view.View

interface RecycleItemClickListener {
    fun onSingleItemClickListener(position: Int)

    fun itemClickWithName(name: String)
}

interface HomeCategoryRecycleItemClickListener {
    fun onHomeCategorySingleItemClickListener(position: Int)
}

interface RecycleItemClickWithView{
    fun itemClickWithView(name: String, position: Int, view: View)
}

interface RecycleItemClick{
    fun onItemClick(name: String, position: Int)

    fun onItemClickWithView( position: Int, view: View)

}

interface RecycleViewItemClick{
    fun onItemClickWithName(name: String, position: Int)

    fun onItemClickWithView(name: String, view: View, position: Int){}
}



