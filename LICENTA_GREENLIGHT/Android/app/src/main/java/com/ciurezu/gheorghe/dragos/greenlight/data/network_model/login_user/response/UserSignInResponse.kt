package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.login_user.response

import com.google.gson.annotations.SerializedName

data class UserSignInResponse(
    @SerializedName("access_token")
    val accessToken : String,
    @SerializedName("refresh_token")
    val refreshToken : String
)
