package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_users_by_coins.response

import com.google.gson.annotations.SerializedName

data class GetAllUsersPctResponse(
    @SerializedName("username")
    val username: String,
    @SerializedName("score")
    val score: Long,
    @SerializedName("photo_url")
    val photoUrl: String
)
