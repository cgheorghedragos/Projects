package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_company

import android.util.Log
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.Voucher
import com.google.gson.annotations.SerializedName

data class CompanyResponse(
    @SerializedName("id")
    var id : Long,
    @SerializedName("name")
    var name: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("vouchers")
    var vouchers: List<Voucher>
)
