package com.shopping.swagbag.utils

import android.content.Context
import android.content.SharedPreferences
import com.shopping.swagbag.auth.signin.SignInModel

class AppUtils(private val context: Context) {

    private val _myPrefName = "sharePrefName"

    fun saveUser(user: SignInModel){
       // val editor: SharedPreferences = context.getSharedPreferences(_myPrefName)
    }
}