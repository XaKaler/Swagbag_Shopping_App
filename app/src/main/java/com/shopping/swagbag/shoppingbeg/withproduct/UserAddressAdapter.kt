package com.shopping.swagbag.shoppingbeg.withproduct

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.SingleUserAddressBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.dummy.User
import com.shopping.swagbag.dummy.UserAddress


class UserAddressAdapter(
    private val context: Context,
    private val data: List<UserAddress>
) :
    RecyclerView.Adapter<UserAddressAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleUserAddressBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: UserAddress){
            with(viewBinding){
                // set text
                userName.text = DummyData().getUser().name
                address.text = singleData.address
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleUserAddressBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount()= data.size
}