package com.shopping.swagbag.common.adapter


import com.shopping.swagbag.common.FreeData
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleCategorySliderBinding
import com.shopping.swagbag.databinding.SingleCategoryToBegBinding
import com.shopping.swagbag.databinding.SingleDealAndPopularBinding
import com.shopping.swagbag.dummy.DummyModel

class CategoryToBegAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<CategoryToBegAdapter.CategoryToBegViewHolder>() {

    inner class CategoryToBegViewHolder(private val viewBinding: SingleCategoryToBegBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyModel){
            with(viewBinding){
                // set image
                Glide.with(context)
                    .load(singleData.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.logo)
                    .into(imgCategoryToBeg)

                tvCategoryToBeg.text = singleData.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryToBegViewHolder {
        return CategoryToBegViewHolder(
            SingleCategoryToBegBinding.inflate(
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