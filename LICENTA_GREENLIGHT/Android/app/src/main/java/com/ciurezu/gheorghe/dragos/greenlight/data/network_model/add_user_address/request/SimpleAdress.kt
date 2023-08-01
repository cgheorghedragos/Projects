package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_address.request

import com.google.gson.annotations.SerializedName

data class SimpleAdress(
    @SerializedName("town")
    var town: String,
    @SerializedName("street")
    var street: String,
    @SerializedName("street_nr")
    var streetNr: Int
)
