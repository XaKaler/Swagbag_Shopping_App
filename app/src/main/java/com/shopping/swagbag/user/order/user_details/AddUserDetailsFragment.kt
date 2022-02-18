package com.shopping.swagbag.user.order.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentAddUserDetailsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.user.auth.UserApi
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel

class AddUserDetailsFragment : BaseFragment<
        FragmentAddUserDetailsBinding,
        UserViewModel,
        UserRepository
        >(FragmentAddUserDetailsBinding::inflate) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentAddUserDetailsBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() = UserRepository(remoteDataSource.getBaseUrl().create(UserApi::class.java))
}