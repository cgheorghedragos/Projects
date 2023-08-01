package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.check_username.response

import com.google.gson.annotations.SerializedName

data class UserAvailabilityResponse(
    @SerializedName("user_exist")
    val userAvailabilityStatus : Boolean
)
