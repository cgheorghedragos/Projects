package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.adapter

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemClickEvent

data class HomeDashboardItem(
    val icon: Int,
    val title:String,
    val listener: ItemClickEvent
)