package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher

import com.google.gson.annotations.SerializedName

data class UserVoucher(
    @SerializedName("voucher")
    val voucher: Voucher,
    @SerializedName("quantity")
    val quantity: Int
)
