package com.shopping.swagbag.common.adapter

import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.HomeCategoryRecycleItemClickListener
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.databinding.SingleCategorySliderBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel

class CategorySliderAdapter(
    private val context: Context,
    private val data: List<DummyModel>,
    private val itemClick: HomeCategoryRecycleItemClickListener

) : RecyclerView.Adapter<CategorySliderAdapter.MyViewHolder>() {

    inner class MyViewHolder(private  val viewBinding: SingleCategorySliderBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        // bind data with view
            fun bind(singleData: DummyModel, itemClick: HomeCategoryRecycleItemClickListener, position: Int){
                with(viewBinding){
                    rvCategoryName.text = singleData.name

                    val imageUrl = singleData.image

                    Log.e("TAG", "bind: $imageUrl", )

                    Glide
                        .with(context)
                        .load(imageUrl)
                        .circleCrop()
                        .into(rvCategorySliderImg)


                   rvCategorySliderImg.setOnClickListener{
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

        if(position == 0){
            val padding: Int = context.resources.getDimensionPixelOffset(R.dimen.screen_padding_15)
            holder.itemView.setPadding(padding, 0,0,0)
        }
    }

    override fun getItemCount() = data.size
}