package com.shopping.swagbag.user.order.user_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentViewUserDetailsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding

class ViewUserDetailsFragment : Fragment(R.layout.fragment_view_user_details) {

    private lateinit var viewBinding: FragmentViewUserDetailsBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentViewUserDetailsBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        with(viewBinding){
            addNewAddress.setOnClickListener{
                findNavController().navigate(R.id.action_viewUserDetailsFragment_to_addUserDetailsFragment)
            }

            btnSelect.setOnClickListener{
                findNavController().navigate(R.id.action_viewUserDetailsFragment_to_home2)
            }

            btnCancel.setOnClickListener{
                findNavController().navigate(R.id.action_viewUserDetailsFragment_to_home2)
            }

        }
        setToolbar()

        setAddresses()
    }

    private fun setAddresses() {
        with(viewBinding){
            rvUserDetails.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = DummyData().getDummyData()?.let { AddressAdapter(context, it) }
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.addresses)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }
}