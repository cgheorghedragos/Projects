package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView

class UploadBroadcastReceiver (private val textView:TextView) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val uploadStatus = intent?.getStringExtra("uploadStatus")
        uploadStatus?.let {
            textView.text = it
        }
    }
}