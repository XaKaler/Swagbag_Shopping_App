package com.shopping.swagbag.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import com.shopping.swagbag.R

class DialogSearchUsingMicrophone: DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_search_using_microphone, container, false)

        val cancel = view.findViewById<ImageView>(R.id.micCancel)
        cancel.setOnClickListener{dismiss()}

        return view
    }
}