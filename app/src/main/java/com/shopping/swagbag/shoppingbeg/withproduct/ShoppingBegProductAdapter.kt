package com.shopping.swagbag.shoppingbeg.withproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleProductOfShoppingBegBinding
import com.shopping.swagbag.dummy.DummyModel


class ShoppingBegProductAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<ShoppingBegProductAdapter.ShoppingBegViewHolder>() {

    inner class ShoppingBegViewHolder(private val viewBinding: SingleProductOfShoppingBegBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: DummyModel){
            with(viewBinding){
                // set imgae
                Glide.with(context)
                    .load(singleData.image)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(productImage)

                // set text
                productName.text = singleData.name
                productDetails.text = singleData.details
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingBegViewHolder {
        return ShoppingBegViewHolder(
            SingleProductOfShoppingBegBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShoppingBegViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount()= data.size
}