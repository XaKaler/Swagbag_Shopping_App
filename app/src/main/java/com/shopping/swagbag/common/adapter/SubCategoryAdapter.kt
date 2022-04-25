package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.category.CategoryModel
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleCategoryDropDownMenuBinding

class SubCategoryAdapter(
    private val context: Context,
    private val data: List<CategoryModel.Result.Category>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleCategoryDropDownMenuBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: CategoryModel.Result.Category, position: Int, itemClick: RecycleViewItemClick){
            with(viewBinding){
                val dataSize: Int = data.size
                if(position == dataSize-1){
                    //view.visibility = View.GONE
                }
                // set text
                tvSubCatName.text = singleData.name

                itemView.setOnClickListener {
                    itemClick.onItemClickWithView("product",itemView,  position)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleCategoryDropDownMenuBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position], position,itemClick)
    }

    override fun getItemCount()= data.size
}