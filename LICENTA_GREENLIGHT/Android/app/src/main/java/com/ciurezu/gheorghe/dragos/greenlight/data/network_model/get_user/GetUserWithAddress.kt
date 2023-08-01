package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_user

import com.google.gson.annotations.SerializedName

data class GetUserWithAddress(
    @SerializedName("id")
    val id: Long,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("coins")
    val coins: Long,
    @SerializedName("score")
    val score: Long,
    @SerializedName("address")
    val address: AddressDto,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("photo_url")
    val photUrl: String
)
