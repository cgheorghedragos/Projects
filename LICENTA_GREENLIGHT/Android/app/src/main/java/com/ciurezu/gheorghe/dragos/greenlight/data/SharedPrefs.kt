package com.ciurezu.gheorghe.dragos.greenlight.data

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class SharedPrefs @Inject constructor(context: Context) {

    companion object {
        const val PREFS_NAME = "app_preferences"
        const val KEY_LOCATION_LAT = "location_lat"
        const val KEY_LOCATION_LNG = "location_lng"
        const val KEY_USERNAME = "username"
        const val KEY_ACCESS_TOKEN = "access_token"
        const val KEY_ADMIN = "is_admin"
        const val KEY_SHOPPER = "is_shopper"
        const val FALLBACK_COORDINATE = 30.0f
        const val MAX_COORDINATE = 90.0f
    }

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun saveUserLocation(location: Location) =
        with(sharedPrefs.edit()) {
            putFloat(KEY_LOCATION_LAT, location.latitude.toFloat())
            putFloat(KEY_LOCATION_LNG, location.longitude.toFloat())
            apply()
        }

    fun getLastUserLocation(): LatLng? {
        val latitude = sharedPrefs.getFloat(KEY_LOCATION_LAT, FALLBACK_COORDINATE)
        val longitude = sharedPrefs.getFloat(KEY_LOCATION_LNG, FALLBACK_COORDINATE)

        if (latitude > MAX_COORDINATE || longitude > MAX_COORDINATE) return null

        return LatLng(latitude.toDouble(), longitude.toDouble())
    }

    fun saveUser(username: String) =
        with(sharedPrefs.edit()) {
            putString(KEY_USERNAME, username)
            apply()
        }

    fun getUsername(): String {
        return sharedPrefs.getString(KEY_USERNAME, "undefined").toString()
    }

    fun saveAccessToken(accessToken: String) =
        with(sharedPrefs.edit()) {
            putString(KEY_ACCESS_TOKEN, accessToken)
            apply()
        }

    fun getAccessToken(): String {
        return sharedPrefs.getString(KEY_ACCESS_TOKEN, "undefined").toString()
    }

    fun saveIsAdmin(isAdmin: Boolean) =
        with(sharedPrefs.edit()) {
            putBoolean(KEY_ADMIN, isAdmin)
            apply()
        }

    fun getIsAdmin(): Boolean {
        return sharedPrefs.getBoolean(KEY_ADMIN, false)
    }

    fun saveIsShopper(isAdmin: Boolean) =
        with(sharedPrefs.edit()) {
            putBoolean(KEY_SHOPPER, isAdmin)
            apply()
        }

    fun getIsShopper(): Boolean {
        return sharedPrefs.getBoolean(KEY_SHOPPER, false)
    }

}