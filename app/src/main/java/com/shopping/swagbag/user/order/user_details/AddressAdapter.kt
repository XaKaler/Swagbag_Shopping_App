package com.shopping.swagbag.user.order.user_details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleUserDetailBinding
import com.shopping.swagbag.dummy.DummyModel

class AddressAdapter(
    private val context: Context,
    private var data: List<AllAddressModel.Result>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<AddressAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleUserDetailBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(singleData: AllAddressModel.Result, position: Int, itemClick: RecycleViewItemClick){
            with(viewBinding){
                name.text = singleData.contactName
                addressType.text = singleData.title

                val fullAddress = "${singleData.address}\n${singleData.city}, ${singleData.state}\n${singleData.pincode}\n\n${singleData.contactMobile}"
                address.text = fullAddress

                //click listners
                edit.setOnClickListener{
                    itemClick.onItemClickWithName("edit", position)
                }

                delete.setOnClickListener{
                    itemClick.onItemClickWithName("delete", position)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleUserDetailBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position], position, itemClick)
    }

    override fun getItemCount()= data.size

    fun updateAddress(updatedAddress: List<AllAddressModel.Result>){
        data = updatedAddress
        notifyDataSetChanged()
    }
}