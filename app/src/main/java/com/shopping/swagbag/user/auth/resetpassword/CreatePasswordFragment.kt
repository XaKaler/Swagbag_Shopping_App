package com.shopping.swagbag.user.auth.resetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentCreatePasswordBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.UserApi
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*

class CreatePasswordFragment :
    BaseFragment<FragmentCreatePasswordBinding, UserViewModel, UserRepository>(
        FragmentCreatePasswordBinding::inflate
    ) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        with(viewBinding){
            save.setOnClickListener { collectInfo() }
        }
    }

    private fun collectInfo() {
        //get arguments
        val args: CreatePasswordFragmentArgs by navArgs()
        val email = args.email
        val otp = args.otp

        //check text fields are empty or not
        with(viewBinding){
            val newPassword = newPassword.text.toString()
            val confirmPassword = confirmPassword.text.toString()

            if(newPassword.isEmpty() || confirmPassword.isEmpty())
                toast("Fill empty fields")
            else{
                if(newPassword != confirmPassword)
                    toast("Password not match")
                else{
                    savePassword(email, otp, newPassword)
                }
            }
        }

    }

    private fun savePassword(email: String, otp: String, password: String) {
        viewModel.passwordReset(email, otp, password).observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()
                    val message = it.value.message
                    toast(message)

                    if (it.value.message != "Invalid email/OTP")
                        findNavController().navigate(R.id.action_global_home2)
                }

                is Resource.Failure -> {
                    stopShowingLoading()
                    tryAgain()
                }
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding){
            // set title
            tvTitle.text = getString(R.string.create_password)

            // back button click
            imgBack.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCreatePasswordBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() = UserRepository(remoteDataSource.getBaseUrl().create(UserApi::class.java))

}