package com.shopping.swagbag.common.adapter

import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.category.CategoryModel
import com.shopping.swagbag.databinding.SingleBestOffersBinding
import com.shopping.swagbag.databinding.SingleBestProductsBinding
import com.shopping.swagbag.databinding.SingleCategoryDropDownBinding
import com.shopping.swagbag.databinding.SingleCategoryDropDownMenuBinding
import com.shopping.swagbag.dummy.DummyModel

class SubCategoryAdapter(
    private val context: Context,
    private val data: List<CategoryModel.Result.Category>
) :
    RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleCategoryDropDownMenuBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: CategoryModel.Result.Category, position: Int){
            with(viewBinding){
                val dataSize: Int = data.size
                if(position == dataSize-1){
                    //view.visibility = View.GONE
                }
                // set text
                tvCatName.text = singleData.name
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
        holder.bind(data[position], position)
    }

    override fun getItemCount()= data.size
}