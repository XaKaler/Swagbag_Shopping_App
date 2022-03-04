package com.shopping.swagbag.common.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.model.BestProductModel
import com.shopping.swagbag.databinding.SingleBestProductsBinding
import com.shopping.swagbag.products.product_details.ProductDetailsFragmentDirections

class BestProductAdapter(
    private val context: Context,
    private val data: List<BestProductModel>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<BestProductAdapter.BestProductViewHolder>() {

    inner class BestProductViewHolder(private val viewBinding: SingleBestProductsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: BestProductModel,itemClick: RecycleViewItemClick , position: Int){
                with(viewBinding){
                    // set imgae
                    /*Glide.with(context)
                        .load(singleData.file)
                        .error(R.drawable.ic_launcher_foreground)
                        .placeholder(R.drawable.logo)
                        .into(imgBestProduct)*/


                    val productAdapter = ProductImageAdapter(context, singleData.file)
                    imgBestProduct.adapter = productAdapter

                    // set text
                    tvBestProductName.text = singleData.name
                    tvBestProductDetails.text = singleData.description

                    itemView.setOnClickListener{
                        itemClick.onItemClickWithName(singleData.name, position)
                    }

                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(SingleBestProductsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        holder.bind(data[position],itemClick, position)
    }

    override fun getItemCount()= data.size
}