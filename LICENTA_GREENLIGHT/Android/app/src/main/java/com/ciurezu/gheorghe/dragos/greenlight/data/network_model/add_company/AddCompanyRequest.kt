package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_company

import com.google.gson.annotations.SerializedName

data class AddCompanyRequest(
    @SerializedName("name")
    var name: String,
    @SerializedName("username")
    var username: String
)
