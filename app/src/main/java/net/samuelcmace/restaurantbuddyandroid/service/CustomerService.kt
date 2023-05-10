package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import org.json.JSONObject

class CustomerService(context: Context) : Service(context) {

    private val mRequestQueue: RequestQueue = Volley.newRequestQueue(this.mContext)

    suspend fun check(): String {

        var response = String()
        val mSessionTokens = mSessionDao.getAll()

        if (mSessionTokens.isNotEmpty()) {

            val url = AppConfig.getServerUrl() + "customer"

            val requestObject = JSONObject()

            val request = object : JsonObjectRequest(
                Method.POST, url, requestObject,
                {
                    if (it.get("errorMessage").equals(null)) {
                        response = it.get("successMessage").toString()
                    } else {
                        Log.i(this.javaClass.toString(), it.get("errorMessage").toString())
                    }
                },
                {
                    Log.i(this.javaClass.toString(), it.localizedMessage.toString())
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Authorization"] = AppConfig.authToken?.token.toString()
                    return super.getHeaders()
                }
            }

            this.mRequestQueue.add(request)
        }

        return response
    }

}
