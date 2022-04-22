package com.shopping.swagbag.user.profile

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.shopping.swagbag.R
import com.shopping.swagbag.common.base.BaseFragment
import com.shopping.swagbag.databinding.FragmentProfileBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import com.shopping.swagbag.service.Resource
import com.shopping.swagbag.service.apis.UserApi
import com.shopping.swagbag.user.auth.UserRepository
import com.shopping.swagbag.user.auth.UserViewModel
import com.shopping.swagbag.user.auth.signin.SignInModel
import com.shopping.swagbag.utils.AppUtils
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : BaseFragment<
        FragmentProfileBinding,
        UserViewModel,
        UserRepository>(
    FragmentProfileBinding::inflate
) {

    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private var maleSelected: Boolean = false
    private val appUtils = context?.let { AppUtils(it) }

    private val myCalendar: Calendar = Calendar.getInstance()

    private val date =
        OnDateSetListener { _, year, month, day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)

            val myFormat = "dd/MM/yyyy"
            val dateFormat = SimpleDateFormat(myFormat, Locale.US)
            viewBinding.birthday.setText(dateFormat.format(myCalendar.time))
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        setUserData()

        // click listeners
        with(viewBinding) {
            txtMale.setOnClickListener {
                maleSelected = true
                //setGender()
            }

            txtFemale.setOnClickListener {
                maleSelected = false
                //setGender()
            }

            birthday.setOnClickListener{
                context?.let { it1 ->
                    DatePickerDialog(
                        it1,
                        date,
                        myCalendar[Calendar.YEAR],
                        myCalendar[Calendar.MONTH],
                        myCalendar[Calendar.DAY_OF_MONTH]
                    ).show()
                }
            }

            btnSaveDetails.setOnClickListener {
                userUpdate()
            }
        }
    }

    private fun userUpdate() {
        with(viewBinding){
            val fName = edtFirstName.text.toString()
            val lName = edtLastName.text.toString()
            val email = emailAddress.text.toString()
            val userId = context?.let { AppUtils(it).getUserId() }
            val token = context?.let { AppUtils(it).getUserToken() }

            Log.e(
                "user data",
                "fName: $fName\n" +
                        "email: $email\n" +
                        "lName: $lName\n" +
                        "userId: $userId\n" +
                        "token: $token\n",
            )

            //update user
            viewModel.userUpdate(userId!!, token!!, fName, lName, email).observe(viewLifecycleOwner) {
                    when (it){
                        is Resource.Loading -> showLoading()

                        is Resource.Success -> {
                            stopShowingLoading()

                            toast("Profile update successfully")
                        }

                        is Resource.Failure -> {
                            stopShowingLoading()

                            toast("try again")

                            Log.e("user", "Update profile: $it", )
                        }
                    }
                }
        }
    }

    private fun setUserData() {
        // get user from app utils
        val appUtils = context?.let { AppUtils(it) }

        if (appUtils!!.isUserLoggedIn()) {
            val user: SignInModel? = appUtils.getUser()

            with(viewBinding) {
                val fName = user?.result?.fname
                val lName = user?.result?.lname
                val mobile = user?.result?.mobile
                val email = user?.result?.email
                val birthDate = ""
                val address = user?.result?.address
                val gender = "Male"

                Log.e(
                    "user data",
                    "username: $fName\n" +
                            "mobile: $mobile\n" +
                            "email: $email\n" +
                            "birthday: $birthDate\n" +
                            "address: $address\n" +
                            "gender: $gender\n",
                )

                //set user data
                edtFirstName.setText(fName)
                edtLastName.setText(lName)
                mobileNo.setText(mobile)
                emailAddress.setText(email)
                location.setText(address)
                birthday.setText(birthDate)

                Log.e("TAG", "setUserData: ${user?.result?.file}")

                context?.let {
                    Glide.with(it)
                        .load(user?.result?.file)
                        .placeholder(R.mipmap.navigation_header_user_img_foreground)
                        .into(imgUser)
                }

                //set gender
                maleSelected = gender == "Male"
            }
        } else
            findNavController().navigate(R.id.action_global_signInFragment)

    }

    private fun setGender() {
        with(viewBinding) {
            if (maleSelected) {
                // set male background and text color
                txtMale.setBackgroundResource(R.drawable.rec_light_red_background_5)
                txtMale.setTextColor(resources.getColor(R.color.white))

                // set female background and text color
                txtFemale.setBackgroundResource(0)
                txtFemale.setTextColor(resources.getColor(R.color.outer_space))
            }
            else{
                // set female background and text color
                txtFemale.setBackgroundResource(R.drawable.rec_light_red_background_5)
                txtFemale.setTextColor(resources.getColor(R.color.white))

                // set male background and text color
                txtMale.setBackgroundResource(0)
                txtMale.setTextColor(resources.getColor(R.color.outer_space))
            }
        }
    }

    private fun setToolbar() {
        with(toolbarBinding) {
            // set title
            tvTitle.text = getString(R.string.profile)

            // back button click
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun getViewModel() = UserViewModel::class.java

    override fun getFragmentRepository() =
        UserRepository(remoteDataSource.getBaseUrl().create(UserApi::class.java))
}