package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_photo.request

import com.google.gson.annotations.SerializedName

data class AddUserPhotoRequest(
    @SerializedName("username")
    val user: String,
    @SerializedName("photo_url")
    val photoUrl: String
)
