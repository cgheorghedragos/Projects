package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_incident

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("photo_url")
    val photoUrl: String,
    @SerializedName("created_on")
    val createdOn: String
)
