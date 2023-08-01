package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng

object LocationHandler {
    private const val REQUEST_LOCATION_INTERVAL = 6000L
    private const val REQUEST_LOCATION_MIN_METERS = 0.001F
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest


    @SuppressLint("MissingPermission")
    fun startWatchingUserLocation(context: Context, onLocation: (location : Location) -> Unit) {
        if (!CheckLocationHandler.hasLocationPermissions(context)) {
            return
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        locationRequest = getLocationRequest()
        locationCallback = getLocationCallBack(onLocation)
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )
    }

    fun stopWatchingUserLocation() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }


    private fun getLocationRequest() =
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, REQUEST_LOCATION_INTERVAL).apply {
            setMinUpdateDistanceMeters(REQUEST_LOCATION_MIN_METERS)
            setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
            setWaitForAccurateLocation(true)
        }.build()

    private fun getLocationCallBack(onLocation: (location: Location) -> Unit) =
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                val locationList = locationResult.locations
                if (locationList.isEmpty()) return

                val location = locationList.last()

                onLocation(location)
            }
        }
}