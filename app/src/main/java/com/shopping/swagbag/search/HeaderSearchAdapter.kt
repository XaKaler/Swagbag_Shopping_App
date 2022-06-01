package com.shopping.swagbag.search

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleProductBinding
import com.shopping.swagbag.products.ProductSearchModel
import com.shopping.swagbag.products.ProductsFragmentDirections

class HeaderSearchAdapter(
    private val context: Context,
    private val data: List<HeaderSearchModel.Result.Product>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<HeaderSearchAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val viewBinding: SingleProductBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: HeaderSearchModel.Result.Product, position: Int, itemClick: RecycleViewItemClick){
            with(viewBinding){
                //for(singleProductResult in singleData.product){
                    //Log.e("search", "search product: $singleProductResult", )
                    Glide.with(context)
                        .load(singleData.file[0].location)
                        .into(imgProduct)

                    // set text
                    tvProductName.text = singleData.name
                    tvProductDetails.text = singleData.shortDesc
                    tvProductPrice.text = singleData.sellingPrice
                    tvProductPriceBeforeDiscount.text = singleData.price

                    tvProductPriceBeforeDiscount.paintFlags = tvProductPriceBeforeDiscount.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    textView22.paintFlags = textView22.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                //}
                // set imgae

                singlePrdouct.setOnClickListener{
                    itemClick.onItemClickWithName(singleData.name, position)
                }
            }
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
        holder.bind(data[position], position, itemClick)
    }

    override fun getItemCount()= data.size
}