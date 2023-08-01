package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_user

import com.google.gson.annotations.SerializedName

data class GetUserResponse(
    @SerializedName("username")
    val username: String,
    @SerializedName("coins")
    val coins: String,
    @SerializedName("photo_url")
    val photoUrl: String
)
