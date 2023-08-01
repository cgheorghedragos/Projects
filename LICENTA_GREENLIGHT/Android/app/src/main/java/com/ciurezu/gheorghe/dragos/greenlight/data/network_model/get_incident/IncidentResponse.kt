package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_incident

import com.google.gson.annotations.SerializedName

data class IncidentResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("created_at_time")
    val createdAtTime: String,
    @SerializedName("created_at_date")
    val createdAtDate: String,
    @SerializedName("photos")
    val photos: List<PhotoResponse>,
    @SerializedName("type")
    val type: String
)
