package com.ciurezu.gheorghe.dragos.greenlight.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File

object FileUploadUtil {
    private const val fileAddName = "GreenLight_"

    fun getFileFromUri(context: Context, uri: Uri): File {
        val originalFile = File(uri.path!!)

        val inputStream = context.contentResolver.openInputStream(uri)
        val fileName = fileAddName +  System.currentTimeMillis()+"_"+originalFile.nameWithoutExtension
        val file = File(context.cacheDir, fileName)
        file.outputStream().use { outputStream ->
            inputStream?.use { it.copyTo(outputStream) }
        }
        return file
    }

    fun getFileListFromUri(data: Intent, context: Context): List<File> {
        val files: MutableList<File> = ArrayList()

        if (data.clipData != null) {
            val count = data.clipData!!.itemCount
            for (i in 0 until count) {
                val imageUri = data.clipData!!.getItemAt(i).uri
                files.add(getFileFromUri(context,imageUri))
            }
        } else if (data.data != null) {
            val imgUri = data.data
            files.add(getFileFromUri(context,imgUri!!))
        }

        return files
    }
}