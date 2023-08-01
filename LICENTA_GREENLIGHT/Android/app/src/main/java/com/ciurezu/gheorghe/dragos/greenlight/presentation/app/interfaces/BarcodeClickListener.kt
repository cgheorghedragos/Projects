package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces

import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.TrashResponse
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.viewmodel.ShoppingViewModel

interface BarcodeClickListener {
    fun onEditClick(trashResponse: TrashResponse)
    fun onDeleteClick(trashResponse: TrashResponse)
}