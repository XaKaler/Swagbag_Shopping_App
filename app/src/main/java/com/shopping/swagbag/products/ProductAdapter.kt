package com.shopping.swagbag.products

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.adapter.BestOfferAdapter
import com.shopping.swagbag.databinding.SingleBestOffersBinding
import com.shopping.swagbag.databinding.SingleProductBinding
import com.shopping.swagbag.dummy.DummyModel

class ProductAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val viewBinding: SingleProductBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyModel){
            with(viewBinding){
                // set imgae
                Glide.with(context)
                    .load(singleData.image)
                    .fitCenter()
                    .into(imgProduct)

                // set text
                tvProductName.text = singleData.name.toString()
                tvProductDetails.text = singleData.details

                singlePrdouct.setOnClickListener{
                    val activity = context as Activity
                    activity.findNavController(R.id.singlePrdouct).navigate(R.id.action_productsFragment_to_productDetailsFragment)
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
        holder.bind(data[position])
    }

    override fun getItemCount()= data.size
}