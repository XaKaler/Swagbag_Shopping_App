package com.shopping.swagbag.user.order.user_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentAddUserDetailsBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.user.auth.UserApi
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel
import com.shopping.swagbag.utils.AppUtils

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
                getUserData()
            }
            btnCancel.setOnClickListener {
                findNavController().navigate(R.id.action_addUserDetailsFragment_to_viewUserDetailsFragment)
            }
        }

        setToolbar()
    }

    private fun getUserData() {
        with(viewBinding) {
            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()
            val address = edtAddress.text.toString()
            val pinCode = edtPinCode.text.toString()
            val state = edtState.text.toString()
            val city = edtCity.text.toString()

            //@todo add lat and lng
            val lat = ""
            val lng = ""

            if (
                name.isNotEmpty() &&
                phone.isNotEmpty() &&
                address.isNotEmpty() &&
                pinCode.isNotEmpty() &&
                state.isNotEmpty() &&
                city.isNotEmpty() &&
                radioGroup.checkedRadioButtonId != -1
            ) {
                val userId = context?.let { AppUtils(it).getUserId() }
                if (userId != null) {

                    //get selected type of address
                    val selectedRadioId = radioGroup.checkedRadioButtonId
                    val selectedRadioButton = radioGroup.findViewById<RadioButton>(selectedRadioId)
                    val title = selectedRadioButton.text.toString()

                    viewModel.addAddress(
                        userId,
                        title,
                        address,
                        "",
                        city,
                        state,
                        "",
                        pinCode,
                        name,
                        phone,
                        lat,
                        lng
                    ).observe(viewLifecycleOwner, Observer {
                        when (it) {
                            is Resource.Loading -> showLoading()

                            is Resource.Success -> {
                                stopShowingLoading()

                                toast(it.value.message)

                                //empty all fields after address save successfully
                                clearFields()
                            }

                            is Resource.Failure -> Log.e("TAG", "getUserData: $it", )
                        }
                    })
                } else {
                    toast("user id not fount")
                }
            } else {
                toast("Fill all fields")
            }
        }
    }

    private fun clearFields() {
        with(viewBinding) {
            edtName.setText("")
            edtPhone.setText("")
            edtAddress.setText("")
            edtPinCode.setText("")
            edtState.setText("")
            edtCity.setText("")
            edtName.setText("")
            radioGroup.clearCheck()
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.add_new_address)

            // back button click
            imgBack.setOnClickListener {
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