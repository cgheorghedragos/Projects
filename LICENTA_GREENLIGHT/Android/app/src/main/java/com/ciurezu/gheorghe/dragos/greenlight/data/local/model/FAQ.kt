package com.ciurezu.gheorghe.dragos.greenlight.data.local.model

import androidx.room.Entity

@Entity(
    tableName = "faq_table",
    primaryKeys = ["title"]
)
data class FAQ(
    val title : String,
    val description: String
)
