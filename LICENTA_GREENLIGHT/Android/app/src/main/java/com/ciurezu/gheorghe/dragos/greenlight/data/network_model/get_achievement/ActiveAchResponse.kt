package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement

import com.google.gson.annotations.SerializedName

data class ActiveAchResponse(
    @SerializedName("current_progress")
    val currentProgress : Long,
    @SerializedName("achievement_lvl")
    val achievementLvl : AchievementLevelResponse,
    )
