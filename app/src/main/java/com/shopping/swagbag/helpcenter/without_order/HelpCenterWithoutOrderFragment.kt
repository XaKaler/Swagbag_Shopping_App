package com.shopping.swagbag.helpcenter.without_order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentHelpCenterWithoutOrderBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class HelpCenterWithoutOrder : Fragment(R.layout.fragment_help_center_without_order) {

    private lateinit var viewBinding: FragmentHelpCenterWithoutOrderBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentHelpCenterWithoutOrderBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.help_center)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}