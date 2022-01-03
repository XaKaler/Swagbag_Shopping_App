package com.shopping.swagbag.common

import android.content.Context
import android.widget.Toast

class ShowToast(var context: Context) {
    fun shortToast(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun longToast(message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}