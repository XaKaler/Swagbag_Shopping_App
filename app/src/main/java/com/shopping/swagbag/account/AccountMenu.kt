package com.shopping.swagbag.account

import android.accounts.Account
import com.shopping.swagbag.R

data class AccountMenu(val icon: Int, val menuName: String)

val accountMenuList = listOf(
    AccountMenu(R.drawable.ic_person, "My Account"),
    AccountMenu(R.drawable.ic_outline_shopping_bag_24, "Orders"),
    AccountMenu(R.drawable.wallet, "Swagbag Wallet"),
    AccountMenu(R.drawable.btm_user, "My Profile"),
    AccountMenu(R.drawable.ic_location, "My Address Book"),
   // AccountMenu(R.drawable.ic_contact_phone, "Communication Preferences"),
)