package com.ciurezu.gheorghe.dragos.greenlight.data.network_model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("httpStatus")
    val httpStatus: String,
    @SerializedName("zonedDateTime")
    val zonedDateTime: String
)
