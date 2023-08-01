package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map

import android.content.Intent
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.viewmodel.MapViewModel

object UpdateMapData {
    private const val REQUEST_INTERVAL = 10000L
    private val handler = Handler()
    private lateinit var dataRunnable: Runnable

    fun startPeriodicDataRetrieval(activity: AppCompatActivity, mapVM: MapViewModel) {
        initFireEventObserver(activity, mapVM)
        initRunnable(mapVM)
        handler.postDelayed(dataRunnable, REQUEST_INTERVAL) // Start after 10 seconds
    }

    private fun initRunnable(mapVM: MapViewModel) {
        dataRunnable = Runnable {
            mapVM.getIncidents()
            handler.postDelayed(dataRunnable, REQUEST_INTERVAL)
        }
    }

    private fun initFireEventObserver(activity: AppCompatActivity, mapVM: MapViewModel) {
        mapVM.shouldFireStart.observe(activity) {
            it?.let {
                if (it) {
                    val sendingBroadcastIntent = Intent()
                    sendingBroadcastIntent.action =
                        activity.getString(R.string.fire_notification_action)
                    activity.sendBroadcast(sendingBroadcastIntent)
                    mapVM.shouldFireStart.postValue(false)
                }
            }
        }
    }

    fun stopPeriodicInterval() {
        handler.removeCallbacks(dataRunnable)
    }

}