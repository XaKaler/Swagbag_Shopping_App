package com.shopping.swagbag.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import com.shopping.swagbag.user.auth.signin.SignInModel

class AppUtils(private val context: Context) {

    private val _myPrefName = "sharePrefName"
    private val _userData = "user_data"
    private val _isUserLogIn = "user_login"

    fun saveUser(user: SignInModel){
       val editor: SharedPreferences.Editor = context.getSharedPreferences(_myPrefName, Context.MODE_PRIVATE).edit()
        editor.putString("user_data", Gson().toJson(user, SignInModel::class.java))
        editor.putBoolean(_isUserLogIn, true)
        editor.apply()
    }

    fun getUser(): SignInModel?{
        val sharedPreferences = context.getSharedPreferences(_myPrefName, Context.MODE_PRIVATE)
        val user =sharedPreferences.getString(_userData, "")

        if(TextUtils.isEmpty(user))
            return null
        return Gson().fromJson(user, SignInModel::class.java)
    }

    fun getUserId(): String{
        val sharedPreferences = context.getSharedPreferences(_myPrefName, Context.MODE_PRIVATE)
        val user =sharedPreferences.getString(_userData, "")

        val singInUser: SignInModel =  Gson().fromJson(user, SignInModel::class.java)
        return singInUser.result.id
    }


    fun getUserToken(): String{
        val sharedPreferences = context.getSharedPreferences(_myPrefName, Context.MODE_PRIVATE)
        val user =sharedPreferences.getString(_userData, "")

        val singInUser: SignInModel =  Gson().fromJson(user, SignInModel::class.java)
        return singInUser.token
    }



    fun isUserLoggedIn(): Boolean{
        val sharedPreferences = context.getSharedPreferences(_myPrefName, Context.MODE_PRIVATE)
        /*
        val isLoggedIn: Boolean = sharedPreferences.getBoolean(_isUserLogIn, false)
        return if(isLoggedIn)
            true
        else{
            val activity  = context as Activity
            activity.findNavController()
            false
        }*/

        return sharedPreferences.getBoolean(_isUserLogIn, false)
    }

    fun logOut(){
        val editor: SharedPreferences.Editor = context.getSharedPreferences(_myPrefName, Context.MODE_PRIVATE).edit()
       // editor.putBoolean(_isUserLogIn, false)
        editor.clear()
        editor.apply()
    }
}
