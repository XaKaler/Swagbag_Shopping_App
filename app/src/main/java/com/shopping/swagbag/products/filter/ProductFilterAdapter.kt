package com.shopping.swagbag.products.filter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClick
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.databinding.SingleFilterBinding
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.ProductFilter


class ProductFilterAdapter(
    private val context: Context,
    private val data: List<ProductFilter>,
    private val itemClick: RecycleItemClick
) :
    RecyclerView.Adapter<ProductFilterAdapter.MyViewHolder>() {

    var selectedPosition = 0

    inner class MyViewHolder(private val viewBinding: SingleFilterBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: ProductFilter, itemClick: RecycleItemClick, position: Int){
                with(viewBinding){
                    filterName.text = singleData.filterName

                    // set first filter background
                    if(singleData.filterName == "Size")
                        singleFilter.setBackgroundColor(ContextCompat.getColor(context, R.color.white))

                    // set backgrond color when user click
                    if(selectedPosition == position){
                        singleFilter.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                    }
                    else{
                        singleFilter.setBackgroundColor(ContextCompat.getColor(context, R.color.cultured))
                    }

                    singleFilter.setOnClickListener{
                        itemClick.onItemClick(singleData.filterName, position)
                        selectedPosition = position
                        notifyDataSetChanged()
                    }
                   // Log.e("TAG", "bind: ${singleData.filterName}", )

                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SingleFilterBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position],itemClick = itemClick, position)
    }

    override fun getItemCount()= data.size
}