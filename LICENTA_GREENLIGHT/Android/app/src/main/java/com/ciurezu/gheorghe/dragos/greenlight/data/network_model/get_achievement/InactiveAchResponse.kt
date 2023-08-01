package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement

import com.google.gson.annotations.SerializedName

data class InactiveAchResponse(
    @SerializedName("created_on")
    var createdOn: String,
    @SerializedName("achievement_lvl")
    val achievementLvl: AchievementLevelResponse,
)
