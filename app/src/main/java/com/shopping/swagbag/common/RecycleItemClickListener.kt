package com.shopping.swagbag.common

interface RecycleItemClickListener {
    fun onSingleItemClickListener(position: Int)
}

interface HomeCategoryRecycleItemClickListener {
    fun onHomeCategorySingleItemClickListener(position: Int)
}

interface RecycleItemClick{
    fun onItemClick(name: String, position: Int)
}



