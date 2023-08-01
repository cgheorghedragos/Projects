package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ciurezu.gheorghe.dragos.greenlight.R

class FireNotificationReceiver : BroadcastReceiver() {
    private val CHANNEL_ID = "New Fire Notification"

    override fun onReceive(context: Context, intent: Intent) {
        val actionString = context.getString(R.string.fire_notification_action)
        if (actionString == intent.action) {
            createNotification(context)
        }
    }

    private fun createNotification(context: Context) {
        createNotificationChannel(context)

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_flood)
            .setContentTitle("Attention Fire")
            .setContentText("A fire start on the map")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1, builder.build())
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Homework Notification Receiver"
            val description = "Notification Receiver"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
            val notificationManager = context.getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}