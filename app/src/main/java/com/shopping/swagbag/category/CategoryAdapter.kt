package com.shopping.swagbag.category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.MainActivity
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleCategoryBinding
import com.shopping.swagbag.dummy.DummyModel


class CategoryAdapter(
    private val context: Context,
    private val data: List<CategoryModel.Result>
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private val viewBinding: SingleCategoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: CategoryModel.Result) {
            with(viewBinding) {
                // set imgae
                /*Glide.with(context)
                    .load(singleData.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(imgCat)*/

                /*constraintLayout2.setBackgroundResource(singleData.background)

                imgCat.setImageResource(singleData.image)
                // set text
                tvCateDetails.text = singleData.details*/

                tvCatName.text = "Shop ${singleData.master.name}"

                cateConstLayout.setOnClickListener {
                    val activity = context as AppCompatActivity
                    activity.findNavController(R.id.cateConstLayout).navigate(R.id.action_categoryFragment_to_productsFragment)

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
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}