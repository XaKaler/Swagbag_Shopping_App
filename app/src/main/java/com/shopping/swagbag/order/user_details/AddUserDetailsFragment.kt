package com.shopping.swagbag.order.user_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentAddUserDetailsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class AddUserDetailsFragment : Fragment(R.layout.fragment_add_user_details) {

    private lateinit var viewBinding: FragmentAddUserDetailsBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentAddUserDetailsBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        with(viewBinding) {
            btnSave.setOnClickListener {
                findNavController().navigate(R.id.action_addUserDetailsFragment_to_viewUserDetailsFragment)
            }
            btnCancel.setOnClickListener {
                findNavController().navigate(R.id.action_addUserDetailsFragment_to_viewUserDetailsFragment)
            }
        }

        setToolbar()
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.add_new_address)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}