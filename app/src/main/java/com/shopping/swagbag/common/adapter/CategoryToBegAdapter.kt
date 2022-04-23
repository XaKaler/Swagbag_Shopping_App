package com.shopping.swagbag.common.adapter


import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.category.CategoryToBegModel
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleCategoryToBegBinding
import com.shopping.swagbag.products.product_details.ProductDetailsFragmentDirections

class CategoryToBegAdapter(
    private val context: Context,
    private val data: List<CategoryToBegModel>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<CategoryToBegAdapter.CategoryToBegViewHolder>() {

    inner class CategoryToBegViewHolder(private val viewBinding: SingleCategoryToBegBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: CategoryToBegModel, itemClick: RecycleViewItemClick, position: Int){
            with(viewBinding){
                // set image
                Glide.with(context)
                    .load(singleData.file)
                    .error(R.drawable.glide_error)
                    .placeholder(R.drawable.glide_error)
                    .into(imgCategoryToBeg)

                tvCategoryToBeg.text = singleData.name

                itemView.setOnClickListener{
                    itemClick.onItemClickWithName("products", position)
                }

                //Log.e("TAG", "bind: ${singleData.name}", )
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
        holder.bind(data[position], itemClick, position)
    }

    override fun getItemCount() = data.size
}