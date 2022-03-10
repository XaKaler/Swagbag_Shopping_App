package com.shopping.swagbag.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.shopping.swagbag.R

class ProgressDialogFragment : DialogFragment() {
    var title: String? = ""
    var message: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.loading_view, container)
        /*
        val tvTitle = view.findViewById<TextView>(R.id.tvProgress_title)
        val tvMessage = view.findViewById<TextView>(R.id.tvProgress_message)*/

        title = requireArguments().getString(ARG_TITLE)
        message = requireArguments().getString(ARG_MESSAGE)

        val imageView = view.findViewById<ImageView>(R.id.loaderImg)

        // Adding the gif here using glide library
        Glide.with(this).load(R.drawable.loading).into(imageView);

        /*
        tvTitle.text = title
        tvMessage.text = message
        */

        return view
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_MESSAGE = "message"
        fun newInstance(): ProgressDialogFragment {
            /*val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_MESSAGE, message)
            fragment.arguments = args*/
            return ProgressDialogFragment()
        }
    }

    init {
        val args = Bundle()
        args.putString(ARG_TITLE, title)
        args.putString(ARG_MESSAGE, message)
        arguments = args
    }
}