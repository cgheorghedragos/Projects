package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.service

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.viewmodel.MapViewModel
import javax.inject.Inject

class UploadImageService : Service() {

    @Inject
    lateinit var mapVM: MapViewModel

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val uri = intent?.getStringExtra("uri")?.let { Uri.parse(it) }
        uri?.let {
           // mapVM.uploadImage(it,applicationContext)
        }
        return START_NOT_STICKY
    }

}