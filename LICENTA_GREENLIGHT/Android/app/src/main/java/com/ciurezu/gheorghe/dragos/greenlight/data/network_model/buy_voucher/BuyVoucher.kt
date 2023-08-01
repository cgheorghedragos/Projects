package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.buy_voucher

import com.google.gson.annotations.SerializedName

data class BuyVoucher(
    @SerializedName("amount")
    val amount: Long,
    @SerializedName("voucher_id")
    val id: Long,
    @SerializedName("username")
    val username: String
)
