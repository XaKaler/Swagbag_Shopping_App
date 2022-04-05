package com.shopping.swagbag.search

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleProductBinding
import com.shopping.swagbag.products.ProductSearchModel
import com.shopping.swagbag.products.ProductsFragmentDirections

class HeaderSearchAdapter(
    private val context: Context,
    private val data: List<HeaderSearchModel.Result>
) :
    RecyclerView.Adapter<HeaderSearchAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val viewBinding: SingleProductBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: HeaderSearchModel.Result, position: Int){
           /* with(viewBinding){
                // set imgae
                Glide.with(context)
                    .load(singleData.file[0].location)
                    .fitCenter()
                    .into(imgProduct)

                // set text
                tvProductName.text = singleData.name
                tvProductDetails.text = singleData.shortDesc
                tvProductPrice.text = singleData.sellingPrice.toString()
                tvProductPriceBeforeDiscount.text = singleData.price.toString()

                tvProductPriceBeforeDiscount.paintFlags = tvProductPriceBeforeDiscount.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                textView22.paintFlags = textView22.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                singlePrdouct.setOnClickListener{
                    val activity = context as Activity
                    val action =
                        ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(
                            singleData.name
                        )
                    activity.findNavController(R.id.singlePrdouct).navigate(action)
                }
            }*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            SingleProductBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount()= data.size
}