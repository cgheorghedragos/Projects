package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.rb

import com.google.gson.annotations.SerializedName

data class GetRecycleBin(
    @SerializedName("id")
    val id: Long,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("created_at_time")
    val createdAtTime: String,
    @SerializedName("created_at_date")
    val createdAtDate: String,
    @SerializedName("type")
    val type: String
)
