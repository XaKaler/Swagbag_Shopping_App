package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.R
import com.shopping.swagbag.category.MasterCategoryModel
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleCategorySliderBinding

class CategorySliderAdapter(
    private val context: Context,
    private val data: List<MasterCategoryModel.Result>,
    private val itemClick: RecycleViewItemClick

) : RecyclerView.Adapter<CategorySliderAdapter.MyViewHolder>() {

    var selectedPosition =-1

    inner class MyViewHolder(private  val viewBinding: SingleCategorySliderBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        // bind data with view
        fun bind(
            singleData: MasterCategoryModel.Result,
            itemClick: RecycleViewItemClick,
            position: Int
        ) {
            with(viewBinding) {
                rvCategoryName.text = singleData.name

                // select when user click
                if (selectedPosition == position) {
                    itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.grey_x11))
                    rvCategoryName.setTextColor(ContextCompat.getColor(context, R.color.black))
                } else {
                    itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.steel_teal))
                        rvCategoryName.setTextColor(ContextCompat.getColor(context, R.color.white))
                    }

                   itemView.setOnClickListener{
                       selectedPosition = position
                       notifyDataSetChanged()
                       itemClick.onItemClickWithName("category", position)
                   }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleCategorySliderBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position], itemClick = itemClick, position)
    }

    override fun getItemCount() = data.size
}