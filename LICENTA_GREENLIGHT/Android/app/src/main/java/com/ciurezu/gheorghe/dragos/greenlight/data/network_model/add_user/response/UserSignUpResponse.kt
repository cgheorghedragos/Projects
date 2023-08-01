package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user.response

import com.google.gson.annotations.SerializedName

data class UserSignUpResponse(
    //@SerializedName("id")
//    val id : Long,
    @SerializedName("username")
    val username: String,
//    @SerializedName("first_name")
//    val firstName: String,
//    @SerializedName("last_name")
//    val lastName :String,
    @SerializedName("coins")
    val coins: Int,
    @SerializedName("password")
    val password: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)