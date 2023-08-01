package com.ciurezu.gheorghe.dragos.greenlight.data.local.mappers

import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.ErrorResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.GreenLightApiResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import javax.inject.Inject


val gson: Gson = Gson()

fun <T> T.toGreenLightResponse() : GreenLightApiResponse<T> =
    GreenLightApiResponse<T>(this,"")

fun <T> T.toErrorResponse(data: ResponseBody) : ErrorResponse = gson.fromJson(data.charStream(),ErrorResponse::class.java)
