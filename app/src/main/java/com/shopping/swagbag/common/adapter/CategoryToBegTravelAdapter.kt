package com.shopping.swagbag.common.adapter


import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleCard21Binding
import com.shopping.swagbag.databinding.SingleCategorySliderBinding
import com.shopping.swagbag.databinding.SingleCategoryToBegBinding
import com.shopping.swagbag.databinding.SingleDealAndPopularBinding
import com.shopping.swagbag.dummy.DummyChild
import com.shopping.swagbag.dummy.DummyModel

class CategoryToBegTravelAdapter(
    private val context: Context,
    private val data: List<DummyChild>
) :
    RecyclerView.Adapter<CategoryToBegTravelAdapter.CategoryToBegViewHolder>() {

    inner class CategoryToBegViewHolder(private val viewBinding: SingleCard21Binding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyChild){
            with(viewBinding){
                // set image
              /*  Glide.with(context)
                    .load(singleData.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(roundedImageView3)*/

                roundedImageView3.setImageResource(singleData.image)

                textView102.text = singleData.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryToBegViewHolder {
        return CategoryToBegViewHolder(
            SingleCard21Binding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryToBegViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}