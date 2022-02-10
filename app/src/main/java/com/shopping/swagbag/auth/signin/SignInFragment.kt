package com.shopping.swagbag.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.auth.UserApi
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentSignInBinding
import com.shopping.swagbag.service.Resource

class SignInFragment :
    BaseFragment<
            FragmentSignInBinding,
            UserViewModel,
            UserRepository>
        (FragmentSignInBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun moveToHome(user: SignInModel) {
        findNavController().navigate(R.id.action_global_home2)
    }

    private fun initViews() {
        with(viewBinding) {
            btnSignIn.setOnClickListener { signInUser() }
            signUp.setOnClickListener { findNavController().navigate(R.id.action_signInFragment_to_signUpFragment) }
            forgotPassword.setOnClickListener { findNavController().navigate(R.id.action_signInFragment_to_resetPassword) }
        }
    }

    private fun signInUser() {
        with(viewBinding) {
            val userName = userName.text.toString()
            val password = password.text.toString()

            viewModel.login(userName, password).observe(viewLifecycleOwner, Observer {
                when (it) {
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()

                        val loginResponse = it.value

                        if (loginResponse.status == "error")
                            toast(loginResponse.message)
                        else {
                            toast(loginResponse.message)
                            moveToHome(it.value)
                        }

                    }

                    is Resource.Failure -> toast("${it.errorBody}")
                }
            })
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSignInBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() =
        UserRepository(remoteDataSource.getBaseUrl().create(UserApi::class.java))

}