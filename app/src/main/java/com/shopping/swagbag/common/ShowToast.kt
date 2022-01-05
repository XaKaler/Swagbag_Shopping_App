package com.shopping.swagbag.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.shopping.swagbag.R
import com.shopping.swagbag.databinding.DialogSearchCameraPermissionBinding

class ShowToast {

    companion object {
        fun long(context: Context, message: String) {
            android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_LONG).show()
        }

        fun short(message: String, context: Context) {
            android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT)
                .show()
        }
    }
}