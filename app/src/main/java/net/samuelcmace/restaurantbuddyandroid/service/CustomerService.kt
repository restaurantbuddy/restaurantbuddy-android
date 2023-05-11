package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import com.android.volley.Request.Method
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import org.json.JSONObject

class CustomerService(context: Context) : Service(context) {

    fun testAuthorization(onSuccess: (response: JSONObject) -> Unit, onError: (errorMessage: String) -> Unit) {
        val url = "${AppConfig.getServerUrl()}/customer"
        authenticatedJSONRequest(url, Method.GET, null, onSuccess, onError)
    }

    fun getMenu(onSuccess: (response: JSONObject) -> Unit, onError: (errorMessage: String) -> Unit) {
        val url = "${AppConfig.getServerUrl()}/customer/items"
        authenticatedJSONRequest(url, Method.GET, null, onSuccess, onError)
    }

}
