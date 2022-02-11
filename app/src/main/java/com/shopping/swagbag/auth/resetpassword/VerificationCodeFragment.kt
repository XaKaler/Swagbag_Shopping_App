package com.shopping.swagbag.auth.resetpassword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shopping.swagbag.R
import com.shopping.swagbag.auth.UserApi
import com.shopping.swagbag.auth.UserRepository
import com.shopping.swagbag.auth.UserViewModel
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentVerificationCodeBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuBinding
import com.shopping.swagbag.service.Resource

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

        with(viewBinding) {
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
            verificationCode4.addTextChangedListener(GenericTextWatcher(verificationCode4, null))

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
            submit.setOnClickListener { verifyOtp() }
        }
    }

    private fun verifyOtp() {

        with(viewBinding) {

            // get arugument that send from reset password fragment with safe args
            val args: VerificationCodeFragmentArgs by navArgs()
            var email = args.email

            // get otp that user enter
            val code1 = verificationCode1.text.toString()
            val code2 = verificationCode1.text.toString()
            val code3 = verificationCode1.text.toString()
            val code4 = verificationCode1.text.toString()

            // check all value are set in edit text
            if (
                code1.isNotEmpty() &&
                code2.isNotEmpty() &&
                code3.isNotEmpty() &&
                code4.isNotEmpty()
            ) {
                val otp = code1 + code2 + code3 + code4

                Log.e("check otp and email", "verifyOtp: $otp\nemail: $email", )

                viewModel.passwordReset(email, otp).observe(viewLifecycleOwner, Observer {
                    when (it) {
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()
                            //@todo match email otp and otp that user enter
                            toast(it.value.message)
                            findNavController().navigate(R.id.action_verificationCodeFragment_to_createPasswordFragment)
                        }

                        is Resource.Failure -> {
                            toast(it.errorBody.toString())
                        }
                    }
                })
            }

            else{
                toast("Fill otp")
            }
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

    class GenericKeyEvent internal constructor(
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

    }
}
