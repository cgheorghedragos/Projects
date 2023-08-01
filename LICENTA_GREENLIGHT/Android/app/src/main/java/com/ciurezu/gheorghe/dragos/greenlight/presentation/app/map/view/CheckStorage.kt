package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object CheckStorage {

      const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123

    fun requestReadExternalStoragePermission(activity: Activity) {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
        }
    }

     fun isReadExternalStoragePermissionGranted(context:Context): Boolean {
        return ContextCompat.checkSelfPermission(context,
            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }
}