package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import com.android.volley.Request
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import org.json.JSONObject

/**
 * Service class dealing with all location-related requests.
 *
 * @constructor Initializes a new instance of LocationService.
 */
class LocationService(context: Context) : Service(context) {

    /**
     * Method to query all active locations from the database.
     *
     * @param onSuccess A lambda function containing code to be executed after a successful API request.
     * @param onError A lambda function containing code to be executed after a failed API request.
     */
    fun getLocations(onSuccess: (response: JSONObject) -> Unit, onError: (errorMessage: String) -> Unit) {
        val url = "${AppConfig.getServerUrl()}/about/locations"
        authenticatedJSONRequest(url, Request.Method.GET, null, onSuccess, onError)
    }

}
