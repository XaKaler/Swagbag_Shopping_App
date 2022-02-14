package com.shopping.swagbag.category

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.MainActivity
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClick
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.databinding.SingleCategoryBinding
import com.shopping.swagbag.dummy.DummyModel


class CategoryAdapter(
    private val context: Context,
    private val data: List<MasterCategoryModel.Result>,
    private val itemClick: RecycleItemClick
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private val viewBinding: SingleCategoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: MasterCategoryModel.Result, position: Int) {
            with(viewBinding) {

                // set image
                Glide.with(context)
                    .load(singleData.file)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(imgCat)

                tvCatName.text = "Shop ${singleData.name}"

                cateConstLayout.setOnClickListener {

                    Log.e("TAG", "bind: itemClick ${singleData.id}", )

                    itemClick.onItemClick(singleData.id, position)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            SingleCategoryBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount() = data.size
}