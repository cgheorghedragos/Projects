package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher

import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Company
import com.google.gson.annotations.SerializedName

data class Voucher(
    @SerializedName("id")
    val id: Long,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("company")
    val company: Company
)