package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import com.android.volley.Request
import kotlinx.coroutines.runBlocking
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import net.samuelcmace.restaurantbuddyandroid.database.entity.Session
import org.json.JSONObject

class AuthenticationService(context: Context) : Service(context) {

    fun login(
        username: String,
        password: String,
        onSuccess: (response: JSONObject) -> Unit,
        onError: (message: String) -> Unit
    ) {
        val url = "${AppConfig.getServerUrl()}/auth/authenticate"
        val requestObject = JSONObject()

        requestObject.put("username", username)
        requestObject.put("password", password)

        this.plainJSONRequest(url, Request.Method.POST, requestObject, {
            this.storeActiveToken(it, onSuccess, onError)
        }, onError)
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
        onSuccess: (response: JSONObject) -> Unit,
        onError: (message: String) -> Unit
    ) {
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

        this.plainJSONRequest(url, Request.Method.POST, requestObject, {
            this.storeActiveToken(it, onSuccess, onError)
        }, onError)
    }

    private fun storeActiveToken(
        response: JSONObject,
        onSuccess: (response: JSONObject) -> Unit,
        onError: (message: String) -> Unit
    ) {
        if (!response.get("jwtToken").equals(null)) {
            runBlocking {
                val authToken = Session(null, response.get("jwtToken").toString())
                AppConfig.authToken = authToken
                mSessionDao.insert(authToken)
            }
            onSuccess(response)
        } else {
            onError("JWT token failed to fetch: " + response.get("errorMessage"))
        }
    }

    fun fetchActiveToken(onSuccess: (message: String) -> Unit, onError: (message: String) -> Unit) {
        runBlocking {
            val mSessionTokens: List<Session> = mSessionDao.getAll()
            if (mSessionTokens.isNotEmpty()) {
                AppConfig.authToken = mSessionTokens.first()
                onSuccess("The stored login token was fetched successfully!")
            } else {
                onError("There are no stored sessions in the database to fetch!")
            }

        }
    }

    fun logout(onSuccess: (message: String) -> Unit) {
        AppConfig.authToken?.let {
            runBlocking {
                mSessionDao.deleteAll()
            }
        }
        AppConfig.authToken = null
        onSuccess("Logout Successful")
    }

}
