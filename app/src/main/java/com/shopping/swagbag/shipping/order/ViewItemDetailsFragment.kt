package com.shopping.swagbag.shipping.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.shopping.swagbag.R
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.databinding.FragmentViewItemDetailsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

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
        setToolbar()
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(com.shopping.swagbag.R.string.order_confirmed)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}