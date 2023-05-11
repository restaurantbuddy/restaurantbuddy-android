package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import com.android.volley.Request
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import org.json.JSONObject

class LocationService(context: Context) : Service(context) {

    fun getLocations(onSuccess: (response: JSONObject) -> Unit, onError: (errorMessage: String) -> Unit) {
        val url = "${AppConfig.getServerUrl()}/about/locations"
        authenticatedJSONRequest(url, Request.Method.GET, null, onSuccess, onError)
    }

}
