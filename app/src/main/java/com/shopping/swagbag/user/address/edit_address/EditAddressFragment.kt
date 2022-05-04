package com.shopping.swagbag.user.address.edit_address

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentEditAddressBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.UserApi
import com.shopping.swagbag.user.address.address_list.AllAddressModel
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel
import com.shopping.swagbag.utils.AppUtils

class EditAddressFragment :
    BaseFragment<FragmentEditAddressBinding, UserViewModel, UserRepository>(
        FragmentEditAddressBinding::inflate
    ) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var getAddress: AllAddressModel.Result

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            setUserData()

            btnSave.setOnClickListener {
                getUserData()
            }
            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        setToolbar()
    }

    private fun setUserData() {
        val args: EditAddressFragmentArgs by navArgs()
        //getAddress = args.address

        with(viewBinding) {
            edtName.setText(getAddress.contactName)
            edtPhone.setText(getAddress.contactMobile)
            edtAddress.setText(getAddress.address)
            edtAddress2.setText(getAddress.address2)
            edtCity.setText(getAddress.city)
            edtPincode.setText(getAddress.pincode)
            edtCountry.setText(getAddress.country)
            edtTitle.setText(getAddress.title)

        }

    }

    private fun getUserData() {
        with(viewBinding) {
            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()
            val address = edtAddress.text.toString()
            val address2 = edtAddress2.text.toString()
            val city = edtCity.text.toString()
            val pincode = edtPincode.text.toString()
            val country = edtCountry.text.toString()
            val title = edtTitle.text.toString()

            //@todo add lat and lng
            val lat = ""
            val lng = ""

            if (
                name.isNotEmpty() &&
                phone.isNotEmpty() &&
                address.isNotEmpty() &&
                //address2.isNotEmpty() &&
                city.isNotEmpty() &&
                pincode.isNotEmpty() &&
                country.isNotEmpty() &&
                title.isNotEmpty()){

                val userId = context?.let { AppUtils(it).getUserId() }

                    getAddress.id.let { it ->
                        if (userId != null)
                        {
                            viewModel.editAddress(
                                userId,
                                title,
                                address,
                                address2,
                                city,
                                "",
                                "",
                                pincode,
                                name,
                                phone,
                                it,
                                "",
                                ""
                            ).observe(viewLifecycleOwner){
                                when(it){
                                    is Resource.Loading -> showLoading()

                                    is Resource.Success -> {
                                        stopShowingLoading()

                                        toast(it.value.message)

                                        //send user to address page
                                        findNavController().popBackStack()
                                    }

                                    is Resource.Failure -> Log.e("TAG", "getUserData: $it", )
                                }
                            }
                        }
                        else toast("User id not found")
                    }

                }
            else {
                toast("Fill all fields")
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = "Edit Address"

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEditAddressBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() =
        UserRepository(remoteDataSource.getBaseUrl().create(UserApi::class.java))

}