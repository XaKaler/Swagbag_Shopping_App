package com.shopping.swagbag.user.auth

import com.shopping.swagbag.common.base.BaseRepository

class UserRepository(private val api: UserApi) : BaseRepository() {

    suspend fun getUserLogin(
        email: String,
        password: String
    ) = safeApiCall { api.getUserLogin(email, password) }

    suspend fun register(
        fName: String,
        lName: String,
        email: String,
        password: String,
        reffer_by: String,
    ) = safeApiCall { api.register(fName, lName, email, password, reffer_by) }

    suspend fun passwordResetEmailSend(
        email: String
    ) = safeApiCall { api.passwordResetEmailSend(email) }

    suspend fun passwordReset(
        email: String,
        otp: String
    ) = safeApiCall { api.passwordReset(email, otp) }

    suspend fun addAddress(
        userid: String,
        title: String,
        address: String,
        address2: String,
        city: String,
        state: String,
        post_office: String,
        pincode: String,
        contact_name: String,
        contact_mobile: String,
        lat: String,
        lng: String,
    ) = safeApiCall {
        api.addAddress(
            userid,
            title,
            address,
            address2,
            city,
            state,
            post_office,
            pincode,
            contact_name,
            contact_mobile,
            lat,
            lng
        )
    }

    suspend fun editAddress(
        userid: String,
        title: String,
        address: String,
        address2: String,
        city: String,
        state: String,
        post_office: String,
        pincode: String,
        contact_name: String,
        contact_mobile: String,
        addressId: String,
        lat: String,
        lng: String,
    ) = safeApiCall {
        api.editAddress(
            userid,
            title,
            address,
            address2,
            city,
            state,
            post_office,
            pincode,
            contact_name,
            contact_mobile,
            addressId,
            lat,
            lng
        )
    }



    suspend fun allAddress(userId: String)= safeApiCall { api.allAddress(userId) }

    suspend fun deleteAddress(addressId: String) = safeApiCall { api.deleteAddress(addressId) }

    suspend fun userUpdate(userid: String, token: String, fName: String, lName: String, email: String) =
        safeApiCall { api.userUpdate(userid, token, fName, lName, email) }

    suspend fun wallet(userId: String,limit: String, page: String) = safeApiCall { api.wallet(userId, limit, page) }
}