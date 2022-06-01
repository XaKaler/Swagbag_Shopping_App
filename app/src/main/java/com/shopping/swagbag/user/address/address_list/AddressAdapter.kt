package com.shopping.swagbag.user.address.address_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.databinding.SingleUserDetailBinding

class AddressAdapter(
    private val context: Context,
    private var data: List<AllAddressModel.Result?>,
    private val itemClick: RecycleViewItemClick
) :
    RecyclerView.Adapter<AddressAdapter.MyViewHolder>() {

    var selectedPosition = -1

    inner class MyViewHolder(private val viewBinding: SingleUserDetailBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {


        fun bind(singleData: AllAddressModel.Result, position: Int, itemClick: RecycleViewItemClick){
            with(viewBinding){
                name.text = singleData.contactName
                addressType.text = singleData.title

                val fullAddress = "${singleData.address}\n${singleData.city}, ${singleData.state}\n${singleData.pincode}\n\n${singleData.contactMobile}"
                address.text = fullAddress

                //when user click on checkbox to set default address
                defaultAddress.isChecked = selectedPosition == position

                //click listners
                edit.setOnClickListener{
                    itemClick.onItemClickWithName("edit", position)
                }

                delete.setOnClickListener{
                    itemClick.onItemClickWithName("delete", position)
                }

                defaultAddress.setOnClickListener {
                    selectedPosition = position
                    itemClick.onItemClickWithName("default", position)
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
        data[position]?.let { holder.bind(it, position, itemClick) }
    }

    override fun getItemCount()= data.size

    fun updateAddress(updatedAddress: MutableList<AllAddressModel.Result>){
        data = updatedAddress
        notifyDataSetChanged()
    }
}