package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user.request

import com.google.gson.annotations.SerializedName

data class UserSignUpRequest (

    @SerializedName("username")
    val username : String,
    @SerializedName("coins")
    val coins : Int,
    @SerializedName("score")
    val score : Int,
    @SerializedName("password")
    val password : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("phone_number")
    val phoneNumber : String
    )