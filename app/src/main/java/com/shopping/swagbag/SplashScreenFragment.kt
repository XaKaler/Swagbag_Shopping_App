package com.shopping.swagbag

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set screen to full screen
        setToFullScreen()

    }

    private fun setToFullScreen() {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

}