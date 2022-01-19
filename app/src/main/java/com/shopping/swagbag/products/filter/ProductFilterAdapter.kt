package com.shopping.swagbag.products.filter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

    inner class MyViewHolder(private val viewBinding: SingleFilterBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: ProductFilter, itemClick: RecycleItemClick, position: Int){
                with(viewBinding){
                    filterName.text = singleData.filterName

                    filterName.setOnClickListener{
                        setButtonBackground(position)
                        itemClick.onItemClick(singleData.filterName, position)
                    }
                }
            }

    }

    private fun setButtonBackground(position: Int) {
        for(i in data.indices){

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