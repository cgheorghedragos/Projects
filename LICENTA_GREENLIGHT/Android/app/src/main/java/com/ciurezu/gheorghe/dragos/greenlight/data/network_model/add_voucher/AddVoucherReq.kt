package com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_voucher

data class AddVoucherReq(
    var quantity: Int,
    var description: String,
    var price: Int,
    var company: CompReq
)
