package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleItemClickListener
import com.shopping.swagbag.databinding.SingleBestProductsBinding
import com.shopping.swagbag.dummy.DummyData
import com.shopping.swagbag.dummy.DummyModel
import com.shopping.swagbag.home.HomeModel
import com.shopping.swagbag.search.SearchFragment

class BestProductAdapter(
    private val context: Context,
    private val data: List<HomeModel.Result.Deal>,
    private val itemClick: RecycleItemClickListener
) :
    RecyclerView.Adapter<BestProductAdapter.BestProductViewHolder>() {

    inner class BestProductViewHolder(private val viewBinding: SingleBestProductsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(singleData: HomeModel.Result.Deal, itemClick: RecycleItemClickListener, position: Int){
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
                    tvBestProductDetails.text = singleData.desc

                    singleBestProduct.setOnClickListener{
                        itemClick.onSingleItemClickListener(position)
                    }
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(SingleBestProductsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        holder.bind(data[position], itemClick, position)
    }

    override fun getItemCount()= data.size
}