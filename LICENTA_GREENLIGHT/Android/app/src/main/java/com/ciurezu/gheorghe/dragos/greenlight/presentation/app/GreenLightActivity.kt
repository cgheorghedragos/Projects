package com.ciurezu.gheorghe.dragos.greenlight.presentation.app

import android.content.IntentFilter
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.FireNotificationReceiver
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.UpdateMapData
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.viewmodel.MapViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import javax.inject.Inject

class GreenLightActivity : AppCompatActivity() {
    @Inject
    lateinit var mapViewModel: MapViewModel
    private val notificationBroadcastReceiver: FireNotificationReceiver =
        FireNotificationReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_green_light_acitivty)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.app_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Find reference to bottom navigation view
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        // Hook your navigation controller to bottom navigation view
        navView.setupWithNavController(navController)

        regRegister()

        UpdateMapData.startPeriodicDataRetrieval(this, mapViewModel)
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(notificationBroadcastReceiver)
        UpdateMapData.stopPeriodicInterval()
    }


    private fun regRegister() {
        val action = getString(R.string.fire_notification_action)
        val intentFilter = IntentFilter(action)
        registerReceiver(notificationBroadcastReceiver, intentFilter)
    }
}
