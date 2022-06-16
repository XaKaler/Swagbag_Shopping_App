package com.shopping.swagbag.common

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shopping.swagbag.R
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.products.filter.ProductFilterAdapter

class Dialogs(private val context: Context, private val inflater: LayoutInflater) {

    fun showFilterBottomSheetDialog(category: String){
        Log.e("filter dialog", "Master category name is : $category")

        val btmDialog = BottomSheetDialog(context, R.style.BottomSheetDialog)
        val infla = inflater.inflate(R.layout.fragment_filter, null, false)
        btmDialog.setContentView(infla.rootView)

        //recycle view items
        val rvFilterOption = infla.findViewById<RecyclerView>(R.id.rvFilterList)
        rvFilterOption.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ProductFilterAdapter(context, DummyData().getProductFilter(), object : RecycleViewItemClick{
                override fun onItemClickWithName(name: String, position: Int) {
                    Log.e("filter button", "filter name is : $name")
                }
            })
        }

        btmDialog.show()
    }
}