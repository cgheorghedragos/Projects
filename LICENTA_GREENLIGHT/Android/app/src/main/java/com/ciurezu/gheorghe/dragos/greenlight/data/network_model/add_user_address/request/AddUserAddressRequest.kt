package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_address.request

import android.location.Address
import com.google.gson.annotations.SerializedName

data class AddUserAddressRequest (
    @SerializedName("user")
    var user : SimpleUser,
    @SerializedName("address")
    var address: SimpleAdress
    )