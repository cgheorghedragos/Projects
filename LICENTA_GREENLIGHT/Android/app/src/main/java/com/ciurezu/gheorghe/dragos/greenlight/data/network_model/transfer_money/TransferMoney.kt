package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.transfer_money

import com.google.gson.annotations.SerializedName

data class TransferMoney(
    val money: Int,
    @SerializedName("user_that_transfer")
    val userThatTransfer: String,
    @SerializedName("user_that_receive")
    val userThatReceive: String
)