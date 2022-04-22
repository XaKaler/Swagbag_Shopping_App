package com.shopping.swagbag.user.auth.resetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.service.apis.UserApi
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentResetPasswordBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding
import com.shopping.swagbag.service.Resource

class ResetPassword : BaseFragment<
        FragmentResetPasswordBinding,
        UserViewModel,
        UserRepository
        >(FragmentResetPasswordBinding::inflate) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()
    }

    private fun initViews() {
        setToolbar()

        //clickListeners
        with(viewBinding) {
            btnSendOtp.setOnClickListener { sendVerificationCode() }
        }
    }

    private fun sendVerificationCode() {
        val email = viewBinding.email.text.toString()

        if(email.isNotEmpty()) {
            viewModel
                .passwordResetEmailSend(email)
                .observe(viewLifecycleOwner, Observer {
                    when (it) {
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()

                            val result = it.value

                            if (result.status == "success") {
                                val action =
                                    ResetPasswordDirections.actionResetPasswordToVerificationCodeFragment(
                                        email
                                    )
                                findNavController().navigate(action)
                                toast(result.message)
                            }
                        }
                        else -> {}
                    }
                })
        }
        else{
            toast("Enter email")
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.reset_password)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentResetPasswordBinding.inflate(inflater, container, false)

    override fun getViewModel()= UserViewModel::class.java

    override fun getFragmentRepository()= UserRepository(remoteDataSource.getBaseUrl().create(
        UserApi::class.java))

}