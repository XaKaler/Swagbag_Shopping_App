package com.shopping.swagbag.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.DialogSearchCameraPermissionBinding

class DialogSearchCameraPermission: DialogFragment(R.layout.dialog_search_camera_permission) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.dialog_search_camera_permission, container, false)
        return view
    }
}