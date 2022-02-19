package com.shopping.swagbag.user.shipping.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentViewItemDetailsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.dummy.DummyData

class ViewItemDetailsFragment : Fragment(R.layout.fragment_view_item_details) {

    private lateinit var viewBinding: FragmentViewItemDetailsBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentViewItemDetailsBinding.bind(view)
        toolbarBinding = viewBinding.include
        initViews()

    }

    private fun initViews() {
        with(viewBinding){
            help.setOnClickListener{
                findNavController().navigate(R.id.action_viewItemDetailsFragment_to_helpCenterWithOrderFragment)
            }
        }

        setProductDetails()

        setToolbar()
    }

    private fun setProductDetails(){
        with(viewBinding){

            context?.let {
                Glide.with(it)
                    .load(DummyData().getSingleImage())
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_swagbug_logo)
                    .into(productImage)
            }

        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.item_details)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}