package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import com.android.volley.Request.Method
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import org.json.JSONObject

class CustomerService(context: Context) : Service(context) {

    private val mRequestQueue: RequestQueue = Volley.newRequestQueue(this.mContext)

    fun testAuthorization(onSuccess: (successMessage: String) -> Unit, onError: (errorMessage: String) -> Unit) {
        val url = "${AppConfig.getServerUrl()}/customer"

        val request = object : JsonObjectRequest(
            Method.GET, url, null,
            {
                if (it.get("errorMessage").equals(null)) {
                    onSuccess(it.get("successMessage").toString())
                } else {
                    onError(it.get("errorMessage").toString())
                }
            },
            {
                onError("The API was unauthorized.")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer ${AppConfig.authToken?.token.toString()}"
                return headers
            }
        }

        mRequestQueue.add(request)
    }

    fun getMenu(onSuccess: (response: JSONObject) -> Unit, onError: (errorMessage: String) -> Unit) {
        val url = "${AppConfig.getServerUrl()}/customer/items"
        jsonRequest(url, Method.GET, null, onSuccess, onError)
    }

    fun jsonRequest(
        url: String,
        httpMethod: Int,
        requestObject: JSONObject?,
        onSuccess: (responseObject: JSONObject) -> Unit,
        onError: (errorMessage: String) -> Unit
    ) {
        val request = object : JsonObjectRequest(
            httpMethod, url, requestObject,
            {
                onSuccess(it)
            },
            {
                onError("The API was unauthorized.")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer ${AppConfig.authToken?.token.toString()}"
                return headers
            }
        }

        mRequestQueue.add(request)
    }

}
