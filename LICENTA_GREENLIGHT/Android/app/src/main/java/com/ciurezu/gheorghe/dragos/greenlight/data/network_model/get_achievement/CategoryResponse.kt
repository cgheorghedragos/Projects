package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String
)
