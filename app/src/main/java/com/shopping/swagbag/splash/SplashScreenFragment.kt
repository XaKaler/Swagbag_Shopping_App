package com.shopping.swagbag.splash

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.shopping.swagbag.R

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