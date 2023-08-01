package com.ciurezu.gheorghe.dragos.greenlight.data.network_model

import com.google.gson.annotations.SerializedName


data class GreenLightApiResponse<T> (
    @SerializedName("payload")
    val payload: T,
    @SerializedName("message")
    val message: String
)