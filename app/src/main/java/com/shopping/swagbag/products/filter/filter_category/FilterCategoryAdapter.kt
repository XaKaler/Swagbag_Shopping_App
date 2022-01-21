package com.shopping.swagbag.products.filter.filter_category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shopping.swagbag.databinding.SingleFilterBrandBinding
import com.shopping.swagbag.databinding.SingleFilterCategoryBinding
import com.shopping.swagbag.dummy.DummyModel


class FilterCategoryAdapter(
    private val context: Context,
    private val data: List<DummyModel>
) :
    RecyclerView.Adapter<FilterCategoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val viewBinding: SingleFilterCategoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: DummyModel){
                with(viewBinding){
                    checkBox.text = singleData.name
                }
            }
    }

    private fun setButtonBackground(position: Int) {
        for(i in data.indices){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SingleFilterCategoryBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount()= data.size
}