package com.shopping.swagbag.user.address.add_address

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentAddAddressBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.service.RemoteDataSource
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.SettingApi
import com.shopping.swagbag.service.apis.UserApi
import com.shopping.swagbag.settings.SettingRepository
import com.shopping.swagbag.settings.SettingViewModelFactory
import com.shopping.swagbag.user.address.address_list.AllAddressModel
import com.shopping.swagbag.user.address.edit_address.EditAddressFragmentArgs
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel
import com.shopping.swagbag.utils.AppUtils
import com.shopping.swagbag.utils.SettingViewModel

class AddAddressFragment : BaseFragment<
        FragmentAddAddressBinding,
        UserViewModel,
        UserRepository
        >(FragmentAddAddressBinding::inflate) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private lateinit var settingViewModel: SettingViewModel
    private lateinit var getAddress: AllAddressModel.Result
    private val allCountries = ArrayList<String>()
    private val countryCodes = ArrayList<String>()
    private var allCities = ArrayList<String>()
    private var country: String = ""
    private var countryCode: String = ""
    private var city: String = ""
    private var addressArgs: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()
    }

    private fun initViews() {
        setToolbar()

        getArgument()

        val settingRepository =
            SettingRepository(RemoteDataSource().getBaseUrl().create(SettingApi::class.java))
        settingViewModel = ViewModelProvider(
            this,
            SettingViewModelFactory(settingRepository)
        )[SettingViewModel::class.java]

        getAllCountries()

        with(viewBinding) {
            btnSave.setOnClickListener {
                getUserData()
            }
            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setCountryCode() {
        with(viewBinding) {
            val arrayAdapter = context?.let { it1 ->
                ArrayAdapter(
                    it1,
                    R.layout.single_text_view,
                    countryCodes
                )
            }

            autoCompleteCountryCode.run {
                setAdapter(arrayAdapter)
                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        countryCode = s.toString()
                    }
                })
            }
        }
    }

    private fun getArgument() {
        val args: AddAddressFragmentArgs by navArgs()
        addressArgs = args.address

        if (addressArgs.isNotEmpty()) {
            getAddress = Gson().fromJson(addressArgs, AllAddressModel.Result::class.java)
            setUserData()
        }
    }

    private fun setUserData() {
        with(viewBinding) {
            edtName.setText(getAddress.contactName)
            edtPhone.setText(getAddress.contactMobile)
            autoCompleteAddress1.setText(getAddress.address)
            edtAddress2.setText(getAddress.address2)
            autoCompleteCity.setText(getAddress.city)
            edtPincode.setText(getAddress.pincode)
            autoCompleteCountry.setText(getAddress.country)
            edtTitle.setText(getAddress.title)
        }
        setCountryCode()
    }

    private fun getAllCountries() {
        settingViewModel.allCountry().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    for (singleCountry in it.value.result) {
                        allCountries.add(singleCountry.name)
                        countryCodes.add(singleCountry.countryCode)
                    }

                    // set county and country code in autocomplete edit text
                    setAllCountries()
                    setCountryCode()
                }

                is Resource.Failure -> {
                    stopShowingLoading()
                }
            }
        }
    }

    private fun setAllCountries() {
        with(viewBinding) {
            val arrayAdapter = context?.let { it1 ->
                ArrayAdapter(
                    it1,
                    R.layout.single_text_view,
                    allCountries
                )
            }

            autoCompleteCountry.run {
                setAdapter(arrayAdapter)
                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        if(viewBinding.autoCompleteCountry.text.toString().isNotEmpty()) {
                            country = s.toString()
                            getAllCity(country)
                        }
                    }
                })
            }
        }
    }

    private fun getAllCity(countryName: String) {
        viewBinding.autoCompleteCity.setText("")
        allCities.clear()
        settingViewModel.allCity(countryName).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()

                    for (singleCity in it.value.result) {
                        allCities.add(singleCity.name)
                    }

                    setAllCities()
                    // set city of related country in autocomplete edit text

                }

                is Resource.Failure -> {
                    stopShowingLoading()
                }
            }
        }
    }

    private fun setAllCities() {
        val arrayAdapter = context?.let { it1 ->
            ArrayAdapter(
                it1,
                R.layout.single_text_view,
                allCities
            )
        }

        viewBinding.autoCompleteCity.run {
            setAdapter(arrayAdapter)
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    city = s.toString()
                }
            })
        }
    }

    private fun getUserData() {
        with(viewBinding) {
            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()
            val address = autoCompleteAddress1.text.toString()
            val address2 = edtAddress2.text.toString()
            val pinCode = edtPincode.text.toString()
            val userCountry = autoCompleteCountry.text.toString()
           // val countryCode = autoCompleteCountryCode.text.toString()
            val userCity = autoCompleteCity.text.toString()
            val title = edtTitle.text.toString()
            val postOffice = ""
            val lat = ""
            val lng = ""

            if (
                name.isNotEmpty() &&
                phone.isNotEmpty() &&
                address.isNotEmpty() &&
                pinCode.isNotEmpty() &&
                userCountry.isNotEmpty() &&
                countryCode.isNotEmpty() &&
                userCity.isNotEmpty() &&
                title.isNotEmpty()

            ) {
                val userId = context?.let { AppUtils(it).getUserId() }
                if (userId != null) {
                    if(addressArgs.isNotEmpty())
                        editAddress(userId, title, address, address2, userCity, userCountry, postOffice, pinCode, name, phone, lat, lng)
                    else
                        addAddress(userId, title, address, address2, userCity, userCountry, postOffice, pinCode, name, phone, lat, lng)

                } else {
                    toast("user id not fount")
                }
            } else {
                toast("Fill all fields")
            }
        }
    }

    private fun editAddress(
        userId: String,
        title: String,
        address: String,
        address2: String,
        userCity: String,
        userCountry: String,
        postOffice: String,
        pinCode: String,
        name: String,
        phone: String,
        lat: String,
        lng: String
    ) {
        viewModel.editAddress(
            userId,
            title,
            address,
            address2,
            userCity,
            userCountry,
            postOffice,
            pinCode,
            name,
            phone,
            getAddress.id,
            lat,
            lng
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

    private fun addAddress(
        userId: String,
        title: String,
        address: String,
        address2: String,
        userCity: String,
        userCountry: String,
        postOffice: String,
        pinCode: String,
        name: String,
        phone: String,
        lat: String,
        lng: String
    ) {
        viewModel.addAddress(
            userId,
            title,
            address,
            address2,
            userCity,
            userCountry,
            "",
            pinCode,
            name,
            phone,
            lat,
            lng
        ).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading()

                is Resource.Success -> {
                    stopShowingLoading()
                    toast(it.value.message)
                    //empty all fields after address save successfully
                    clearFields()
                }

                is Resource.Failure -> Log.e("TAG", "getUserData: $it")
            }
        }
    }

    private fun clearFields() {
        with(viewBinding) {
            edtName.setText("")
            edtPhone.setText("")
            autoCompleteAddress1.setText("")
            edtAddress2.setText("")
            edtPincode.setText("")
            autoCompleteCountry.setText("")
            autoCompleteCity.setText("")
            edtTitle.setText("")
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
    ) = FragmentAddAddressBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() = UserRepository(
        remoteDataSource.getBaseUrl().create(
            UserApi::class.java
        )
    )
}