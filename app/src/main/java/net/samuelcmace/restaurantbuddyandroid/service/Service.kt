package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import androidx.room.Room
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import net.samuelcmace.restaurantbuddyandroid.database.DatabaseManager
import net.samuelcmace.restaurantbuddyandroid.database.dao.ItemDao
import net.samuelcmace.restaurantbuddyandroid.database.dao.SessionDao
import org.json.JSONObject

/**
 * Abstract class representing any custom service that the app may use.
 */
abstract class Service(context: Context) {

    /**
     * The Android context that the service needs to reference.
     */
    private var mContext: Context

    /**
     * The database manager instance for the service.
     */
    private var mDatabaseManager: DatabaseManager

    /**
     * The DAO instance referencing the Session table used by the class.
     */
    protected var mSessionDao: SessionDao

    /**
     * The DAO instance referencing the Item table used by the class.
     */
    protected var mItemDao: ItemDao

    /**
     * The session queue used by the Android Volley framework in all REST-ful HTTP requests.
     */
    private var mRequestQueue: RequestQueue

    /**
     * Initializes a new instance of Service.
     */
    init {
        this.mContext = context
        this.mDatabaseManager = Room.databaseBuilder(
            context,
            DatabaseManager::class.java, DatabaseManager.DATABASE_NAME
        ).build()

        this.mSessionDao = this.mDatabaseManager.sessionDao()
        this.mItemDao = this.mDatabaseManager.itemDao()

        this.mRequestQueue = Volley.newRequestQueue(this.mContext)
    }

    /**
     * Method called to initiate a new JSON request with the authentication token stored in the singleton object.
     *
     * @param url The URL to query.
     * @param httpMethod The HTTP method for the request (such as GET, POST, PUT, or DELETE).
     * @param requestObject The JSON object to be sent to the server when initiating the request.
     * @param onSuccess A lambda function containing code to be executed after a successful API request.
     * @param onError A lambda function containing code to be executed after a failed API request.
     */
    protected fun authenticatedJSONRequest(
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

    /**
     * Method called to initiate a new JSON request without any authentication.
     *
     * @param url The URL to query.
     * @param httpMethod The HTTP method for the request (such as GET, POST, PUT, or DELETE).
     * @param requestObject The JSON object to be sent to the server when initiating the request.
     * @param onSuccess A lambda function containing code to be executed after a successful API request.
     * @param onError A lambda function containing code to be executed after a failed API request.
     */
    protected fun plainJSONRequest(
        url: String,
        httpMethod: Int,
        requestObject: JSONObject?,
        onSuccess: (responseObject: JSONObject) -> Unit,
        onError: (errorMessage: String) -> Unit
    ) {
        val request = JsonObjectRequest(
            httpMethod, url, requestObject,
            {
                onSuccess(it)
            },
            {
                onError("The API was unauthorized.")
            }
        )

        mRequestQueue.add(request)
    }

}
