package com.shopping.swagbag.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.google.gson.Gson
import com.shopping.swagbag.auth.signin.SignInModel

class AppUtils(private val context: Context) {

    private val _myPrefName = "sharePrefName"
    private val _userData = "user_data"

    fun saveUser(user: SignInModel){
       val editor: SharedPreferences.Editor = context.getSharedPreferences(_myPrefName, Context.MODE_PRIVATE).edit()
        editor.putString("user_data", Gson().toJson(user, SignInModel::class.java))
        editor.apply()
    }

    fun getUser(): SignInModel?{
        val sharedPreferences = context.getSharedPreferences(_myPrefName, Context.MODE_PRIVATE)
        val user =sharedPreferences.getString(_userData, "")

        if(TextUtils.isEmpty(user))
            return null
        return Gson().fromJson(user, SignInModel::class.java)
    }

    fun isUserLoggedIn(): Boolean{
        val sharedPreferences = context.getSharedPreferences(_myPrefName, Context.MODE_PRIVATE)
        val user =sharedPreferences.getString(_userData, "")
        return user != null
    }
}
