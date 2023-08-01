package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_incident

import com.google.gson.annotations.SerializedName

data class AddIncidentRequest(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("type")
    val type: String
)
