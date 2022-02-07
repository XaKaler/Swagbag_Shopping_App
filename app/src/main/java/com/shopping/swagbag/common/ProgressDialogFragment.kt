package com.shopping.swagbag.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.shopping.swagbag.R

class ProgressDialogFragment : DialogFragment() {
    var title: String? = ""
    var message: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.loading_view, container)
        val tvTitle = view.findViewById<TextView>(R.id.tvProgress_title)
        val tvMessage = view.findViewById<TextView>(R.id.tvProgress_message)
        title = requireArguments().getString(ARG_TITLE)
        message = requireArguments().getString(ARG_MESSAGE)
        tvTitle.text = title
        tvMessage.text = message
        return view
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_MESSAGE = "message"
        fun newInstance(title: String?, message: String?): ProgressDialogFragment {
            val fragment = ProgressDialogFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_MESSAGE, message)
            fragment.arguments = args
            return fragment
        }
    }

    init {
        val args = Bundle()
        args.putString(ARG_TITLE, title)
        args.putString(ARG_MESSAGE, message)
        arguments = args
    }
}