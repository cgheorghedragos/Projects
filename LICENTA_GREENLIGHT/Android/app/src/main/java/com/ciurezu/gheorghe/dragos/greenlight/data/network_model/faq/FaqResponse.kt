package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.faq

import com.google.gson.annotations.SerializedName

data class FaqResponse(
    @SerializedName("id")
    val id:String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String
)
