package com.shopping.swagbag.user.auth.resetpassword

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentVerificationCodeBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.UserApi
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel

class VerificationCodeFragment : BaseFragment<
        FragmentVerificationCodeBinding,
        UserViewModel,
        UserRepository>(FragmentVerificationCodeBinding::inflate) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarBinding = viewBinding.include


        initViews()
    }

    private fun initViews() {
        setToolbar()

        viewBinding.submit.setOnClickListener { verifyOtp() }

        /*with(viewBinding) {
            //GenericTextWatcher here works only for moving to next EditText when a number is entered
//first parameter is the current EditText and second parameter is next EditText
            verificationCode1.addTextChangedListener(
                GenericTextWatcher(
                    verificationCode1,
                    verificationCode2
                )
            )
            verificationCode2.addTextChangedListener(
                GenericTextWatcher(
                    verificationCode2,
                    verificationCode3
                )
            )
            verificationCode3.addTextChangedListener(
                GenericTextWatcher(
                    verificationCode3,
                    verificationCode4
                )
            )

            verificationCode4.addTextChangedListener(
                GenericTextWatcher(
                    verificationCode4,
                    verificationCode5
                )
            )

            verificationCode5.addTextChangedListener(
                GenericTextWatcher(
                    verificationCode5,
                    verificationCode6
                )
            )

            verificationCode4.addTextChangedListener(GenericTextWatcher(verificationCode6, null))

//GenericKeyEvent here works for deleting the element and to switch back to previous EditText
//first parameter is the current EditText and second parameter is previous EditText
            verificationCode1.setOnKeyListener(
                GenericKeyEvent(
                    verificationCode1,
                    null
                )
            )

            verificationCode2.setOnKeyListener(
                GenericKeyEvent(
                    verificationCode2,
                    verificationCode1
                )
            )

            verificationCode3.setOnKeyListener(
                GenericKeyEvent(
                    verificationCode3,
                    verificationCode2
                )
            )
            verificationCode4.setOnKeyListener(
                GenericKeyEvent(
                    verificationCode4,
                    verificationCode3
                )
            )

            verificationCode5.setOnKeyListener(
                GenericKeyEvent(
                    verificationCode5,
                    verificationCode4
                )
            )

            verificationCode6.setOnKeyListener(
                GenericKeyEvent(
                    verificationCode6,
                    verificationCode5
                )
            )

            submit.setOnClickListener { verifyOtp() }
        }*/
    }

    private fun verifyOtp() {
        // get arguument that send from reset password fragment with safe args
        val args: VerificationCodeFragmentArgs by navArgs()
        val email = args.email

        val otp = viewBinding.verificationCode.text.toString()

        // check all value are set in edit text
        if (otp.isNotEmpty()) {
            Log.e("check otp and email", "verifyOtp: $otp\nemail: $email")
/*
            viewModel.passwordReset(email, otp).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> showLoading()

                    is Resource.Success -> {
                        stopShowingLoading()
                        //@todo match email otp and otp that user enter
                        toast(it.value.message)
                        if (it.value.message != "Invalid email/OTP")
                            findNavController().navigate(R.id.action_verificationCodeFragment_to_createPasswordFragment)
                    }

                    is Resource.Failure -> {
                        toast(it.errorBody.toString())
                    }
                }
            }*/

            val action = VerificationCodeFragmentDirections.actionVerificationCodeFragmentToCreatePasswordFragment(email, otp)
            findNavController().navigate(action)

        } else {
            toast("Enter OTP first")
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            tvTitle.text = getString(R.string.verification_code)

            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }


    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentVerificationCodeBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() = UserRepository(
        remoteDataSource.getBaseUrl().create(
            UserApi::class.java
        )
    )

    /*class GenericKeyEvent internal constructor(
        private val currentView: EditText,
        private val previousView: EditText?
    ) : View.OnKeyListener {
        override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
            if (
                event!!.action == KeyEvent.ACTION_DOWN &&
                keyCode == KeyEvent.KEYCODE_DEL &&
                currentView.id != R.id.verificationCode1 &&
                currentView.text.isEmpty()
            ) {
                //If current is empty then previous EditText's number will also be deleted
                previousView!!.text = null
                previousView.requestFocus()
                return true
            }
            return false
        }
    }
    class GenericTextWatcher internal constructor(
        private val currentView: View,
        private val nextView: View?
    ) :
        TextWatcher {
        override fun afterTextChanged(editable: Editable) { // TODO Auto-generated method stub
            val text = editable.toString()
            when (currentView.id) {
                R.id.verificationCode1 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.verificationCode2 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.verificationCode3 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.verificationCode4 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.verificationCode5 -> if (text.length == 1) nextView!!.requestFocus()
                //You can use EditText4 same as above to hide the keyboard
            }
        }

        override fun beforeTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) { // TODO Auto-generated method stub
        }

        override fun onTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) { // TODO Auto-generated method stub
        }

    }*/
}
