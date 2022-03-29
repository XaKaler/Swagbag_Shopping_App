package com.shopping.swagbag.user.order.with_items

import android.app.Activity
import android.content.Context
import androidx.navigation.fragment.findNavController
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleOrderItemBinding
import com.shopping.swagbag.dummy.DummyModel

class OrderItemsAdapter(
    private val context: Context,
    private val data: List<OrderModel.OrderModelItem.Product>
) :
    RecyclerView.Adapter<OrderItemsAdapter.BestProductViewHolder>() {

    inner class BestProductViewHolder(private val viewBinding: SingleOrderItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: OrderModel.OrderModelItem.Product, position: Int){
                with(viewBinding){
                    // set image
                    Glide.with(context)
                        .load(singleData.product.file[0].location)
                        .into(productImg)


                    // set text
                    productName.text = singleData.productname
                    productDesc.text = singleData.product.desc
                    productPrice.text = singleData.product.price.toString()
                    deliveryTime.text = singleData.product.endDate

                    val activity = context as Activity
                    cancel.setOnClickListener {
                        activity.findNavController(R.id.cancel)
                            .navigate(R.id.action_orderWithItemsFragment_to_cancellationOrderFragment)
                    }

                    itemView.setOnClickListener {
                        activity.findNavController(R.id.cancel)
                            .navigate(R.id.action_orderWithItemsFragment_to_viewItemDetailsFragment)
                    }
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(SingleOrderItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount()= data.size
}