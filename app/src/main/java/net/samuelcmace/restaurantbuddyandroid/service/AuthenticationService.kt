package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import com.android.volley.Request
import kotlinx.coroutines.runBlocking
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import net.samuelcmace.restaurantbuddyandroid.database.entity.Session
import org.json.JSONObject

/**
 * Custom service class associated with all authentication-related requests (such as logging in and out, as well as
 * new customer registration).
 *
 * @constructor Initializes a new instance of AuthenticationService.
 */
class AuthenticationService(context: Context) : Service(context) {

    /**
     * Method called to log a new user into the system.
     *
     * @param username The username sent by the user.
     * @param password The password sent by the user
     * @param onSuccess A lambda function containing code to be executed after a successful API request.
     * @param onError A lambda function containing code to be executed after a failed API request.
     */
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

    /**
     * Method called to register a new user into the system.
     *
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param email The user's email address.
     * @param phone The user's phone number.
     * @param address The user's US-based postal address.
     * @param city The user's US-based city.
     * @param state The user's US-based state.
     * @param zip The user's US-based zip code.
     * @param username The username for the new login.
     * @param password The password for the new login.
     * @param onSuccess A lambda function containing code to be executed after a successful API request.
     * @param onError A lambda function containing code to be executed after a failed API request.
     */
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

    /**
     * Method to store the active token both in the database (for applications that have just been opened)
     * and in the singleton class (for smooth switching between activities).
     *
     * @param response The JSON object returned after the JWT token object has been sent.
     * @param onSuccess A lambda function containing code to be executed after a successful API request.
     * @param onError A lambda function containing code to be executed after a failed API request.
     */
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

    /**
     * Method to fetch the active token from the database and store it in the singleton object.
     *
     * @param onSuccess A lambda function containing code to be executed after a successful API request.
     * @param onError A lambda function containing code to be executed after a failed API request.
     */
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

    /**
     * Method called to log the user out of the application.
     *
     * @param onSuccess A lambda function containing code to be executed after a successful API request.
     */
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
