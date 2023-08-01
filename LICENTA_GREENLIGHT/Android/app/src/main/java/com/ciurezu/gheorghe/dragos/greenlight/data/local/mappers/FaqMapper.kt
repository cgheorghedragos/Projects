package com.ciurezu.gheorghe.dragos.greenlight.data.local.mappers

import com.ciurezu.gheorghe.dragos.greenlight.data.local.model.FAQ
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.faq.FaqResponse

fun FAQ.toFaqResponse(): FaqResponse =
    FaqResponse(
        title = title,
        description = description,
        id = "null"
    )

fun FaqResponse.toFaq(): FAQ =
    FAQ(
        title = title,
        description = description
    )