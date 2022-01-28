package com.shopping.swagbag.profile

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.FragmentProfileBinding
import com.shopping.swagbag.databinding.ToolbarWithNoMenuWhiteBgBinding
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {

    private lateinit var viewBinding: FragmentProfileBinding
    private lateinit var toolbarBinding: ToolbarWithNoMenuWhiteBgBinding
    private var maleSelected: Boolean = false

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

        viewBinding = FragmentProfileBinding.bind(view)
        toolbarBinding = viewBinding.include

        initViews()


    }

    private fun initViews() {
        setToolbar()

        // click listeners
        with(viewBinding) {
            txtMale.setOnClickListener {
                maleSelected = true
                setGender()
            }

            txtFemale.setOnClickListener {
                maleSelected = false
                setGender()
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

            btnSaveDetails.setOnClickListener(this@ProfileFragment)
        }
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

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnSaveDetails -> {
                findNavController().navigate(R.id.action_profileFragment_to_home2)
            }
        }
    }
}