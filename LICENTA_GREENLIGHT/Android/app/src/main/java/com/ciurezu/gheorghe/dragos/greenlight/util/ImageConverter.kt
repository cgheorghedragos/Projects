package com.ciurezu.gheorghe.dragos.greenlight.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources

object ImageConverter {
    private const val WIDTH_ICON_SIZE_DP = 40;
    private const val HEIGHT_ICON_SIZE_DP = 40;
    fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int): Bitmap? {
        val density = context.resources.displayMetrics.density
        return convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId), density)
    }

    fun convertDrawableToBitmap(sourceDrawable: Drawable?, density: Float): Bitmap? {

        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
// copying drawable object to not manipulate on the same reference
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                (WIDTH_ICON_SIZE_DP * density).toInt(), (HEIGHT_ICON_SIZE_DP * density).toInt(),
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }
}