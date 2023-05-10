package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.runBlocking
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import net.samuelcmace.restaurantbuddyandroid.database.entity.Session
import org.json.JSONObject

class AuthenticationService(context: Context) : Service(context) {

    private var mRequestQueue: RequestQueue = Volley.newRequestQueue(this.mContext)

    fun login(username: String, password: String, onComplete: () -> Unit) {
        val url = "${AppConfig.getServerUrl()}/auth/authenticate"

        val requestObject = JSONObject()

        requestObject.put("username", username)
        requestObject.put("password", password)

        val request = authenticate(url, requestObject, onComplete)

        this.mRequestQueue.add(request)
    }

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
        address: String,
        city: String,
        state: String,
        zip: String,
        username: String,
        password: String,
        onComplete: () -> Unit
    ) {
        val queue = Volley.newRequestQueue(this.mContext)
        val url = "${AppConfig.getServerUrl()}/auth/register/customer/new"

        val requestObject = JSONObject()

        requestObject.put("firstName", firstName)
        requestObject.put("lastName", lastName)
        requestObject.put("email", email)
        requestObject.put("phone", phone)

        requestObject.put("address", address)
        requestObject.put("city", city)
        requestObject.put("state", state)
        requestObject.put("zip", zip)

        requestObject.put("username", username)
        requestObject.put("password", password)

        val request = authenticate(url, requestObject, onComplete)

        this.mRequestQueue.add(request)
    }

    fun logout(onComplete: () -> Unit) {
        AppConfig.authToken?.let {
            runBlocking {
                mSessionDao.deleteAll(it)
            }
        }
        AppConfig.authToken = null
        onComplete()
    }

    private fun authenticate(requestUrl: String, requestObject: JSONObject, onComplete: () -> Unit) = JsonObjectRequest(
        Request.Method.POST, requestUrl, requestObject,
        { response ->
            if (!response.get("jwtToken").equals(null)) {
                val authToken = Session(null, response.get("jwtToken").toString())
                AppConfig.authToken = authToken
                runBlocking {
                    mSessionDao.insertAll(authToken)
                }
                onComplete()
            } else {
                Log.i("AuthenticationService -- JSON Error", response.get("errorMessage").toString())
            }
        },
        {
            Log.i("AuthenticationService -- HTTP Error", it.toString())
        })

}
