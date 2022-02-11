package com.shopping.swagbag.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.auth.UserApi
import com.shopping.swagbag.auth.UserRepository
import com.shopping.swagbag.auth.UserViewModel
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentSignUpBinding
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.utils.AppUtils


class SignUpFragment : BaseFragment<
        FragmentSignUpBinding,
        UserViewModel,
        UserRepository
        >(FragmentSignUpBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            signIn.setOnClickListener { findNavController().navigate(R.id.action_signUpFragment_to_signInFragment) }
            btnSignUp.setOnClickListener { signUp() }
        }
    }

    private fun signUp() {
        with(viewBinding) {
            val fName = firstName.text.toString()
            val lName = lastName.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()
            val reffer_by = ""

            // first check any view is not empty
            if (
                fName.isNotEmpty() &&
                lName.isNotEmpty() &&
                email.isNotEmpty() &&
                password.isNotEmpty()
            ) {
                viewModel.register(fName, lName, email, password, reffer_by)
                    .observe(viewLifecycleOwner, Observer {
                        when (it) {
                            is Resource.Loading -> showLoading()

                            is Resource.Success -> {
                                stopShowingLoading()

                                val result = it.value

                                if (result.status == "success") {
                                    //move user to home page
                                    //@todo save user in app utils
                                   // saveUser(email, password)
                                    moveToHome()
                                }

                                toast(result.message)
                            }

                            is Resource.Failure -> {
                                toast(it.errorBody.toString())
                            }
                        }
                    })
            }
        }
    }

    private fun saveUser(email: String, password: String) {
        //@todo save user to app utils
        viewModel.login(email, password).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    context?.let { context -> AppUtils(context).saveUser(it.value) }
                    moveToHome()
                }

                is Resource.Failure -> {
                    toast(it.errorBody.toString())
                }
                else -> {}
            }
        })
    }

    private fun moveToHome() {
        findNavController().navigate(R.id.action_signUpFragment_to_home2)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSignUpBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() =
        UserRepository(remoteDataSource.getBaseUrl().create(UserApi::class.java))
}