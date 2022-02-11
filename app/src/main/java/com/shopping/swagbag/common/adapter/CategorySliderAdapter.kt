package com.shopping.swagbag.common.adapter

import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.category.MasterCategoryModel
import com.shopping.swagbag.common.HomeCategoryRecycleItemClickListener
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.databinding.SingleCategorySliderBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel

class CategorySliderAdapter(
    private val context: Context,
    private val data: List<MasterCategoryModel.Result>,
    private val itemClick: HomeCategoryRecycleItemClickListener

) : RecyclerView.Adapter<CategorySliderAdapter.MyViewHolder>() {

    var selectedPosition =-1

    inner class MyViewHolder(private  val viewBinding: SingleCategorySliderBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        // bind data with view
            fun bind(singleData: MasterCategoryModel.Result, itemClick: HomeCategoryRecycleItemClickListener, position: Int){
                with(viewBinding){
                    rvCategoryName.text = singleData.name

                    // select when user click
                    if(selectedPosition == position){
                        itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.grey_x11))
                        rvCategoryName.setTextColor(ContextCompat.getColor(context, R.color.black))
                    }
                    else{
                        itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.steel_teal))
                        rvCategoryName.setTextColor(ContextCompat.getColor(context, R.color.white))
                    }

                   itemView.setOnClickListener{
                       selectedPosition = position
                       notifyDataSetChanged()
                       itemClick.onHomeCategorySingleItemClickListener(position)
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