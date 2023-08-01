package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_user

import com.google.gson.annotations.SerializedName

data class AddressDto(
    var id: Long,
    val town: String,
    val street: String,
    @SerializedName("street_nr")
    val streetNr: Long
)
