package com.shopping.swagbag.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.RecycleViewItemClick
import com.shopping.swagbag.common.model.BestProductModel
import java.util.*


class ProductImageAdapter(
    private val context: Context,
    private val image: List<BestProductModel.File>,
    private val itemClick: RecycleViewItemClick
) : PagerAdapter() {

    override fun getCount() = image.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
         val mLayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // inflating the item.xml
        val itemView: View = mLayoutInflater.inflate(R.layout.single_image_slider, container, false)

        // referencing the image view from the item.xml file
        val imageView: ImageView = itemView.findViewById<View>(R.id.imgAutoSlider) as ImageView

        // setting the image in the imageView
        Glide.with(context)
            .load(image[position].location)
            .error(R.drawable.glide_error)
            .placeholder(R.drawable.glide_error)
            .into(imageView)

        //click liseners
        imageView.setOnClickListener {
            itemClick.onItemClickWithName("image click", position)
        }

        // Adding the View
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}