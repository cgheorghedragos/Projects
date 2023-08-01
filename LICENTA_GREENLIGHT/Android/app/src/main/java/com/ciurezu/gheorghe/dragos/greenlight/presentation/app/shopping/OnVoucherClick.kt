package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping

import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.Voucher

interface OnVoucherClick {
    fun onClick(voucher: Voucher)
}