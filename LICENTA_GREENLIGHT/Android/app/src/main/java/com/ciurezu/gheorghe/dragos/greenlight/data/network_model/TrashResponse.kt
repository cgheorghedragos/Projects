package com.ciurezu.gheorghe.dragos.greenlight.data.network_model

import com.google.gson.annotations.SerializedName

data class TrashResponse(
    @SerializedName("bar_code")
    val barcode: String,
    @SerializedName("category")
    val categoryName: String
)