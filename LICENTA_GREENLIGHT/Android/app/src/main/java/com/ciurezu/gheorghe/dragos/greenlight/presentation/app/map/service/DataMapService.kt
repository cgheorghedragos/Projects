package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.viewmodel.MapViewModel
import javax.inject.Inject

class DataMapService : Service() {
    @Inject
    lateinit var mapVM: MapViewModel
    private val mHandler = Handler()

    private val mRunnable = object : Runnable {
        override fun run() {
            // Perform the desired operation here
           // mapVM.req()

            // Schedule the next execution after 10 seconds
            mHandler.postDelayed(this, 10000)
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        // Return null as this is not a bound service
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Perform any background tasks here

        // Return START_STICKY to ensure the service keeps running even if resources are reclaimed
        return START_STICKY
    }

    override fun onDestroy() {
        // Cleanup tasks, if any
        super.onDestroy()
    }

}