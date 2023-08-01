package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement

import com.google.gson.annotations.SerializedName

data class AchievementLevelResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("category")
    val category: CategoryResponse,
    @SerializedName("level")
    val level: Long,
    @SerializedName("maxPoints")
    val maxPoints : Long,
    @SerializedName("imgLink")
    val imgLink :String,
    @SerializedName("achievementName")
    val achievementName: String
)
