package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import androidx.room.Room
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import net.samuelcmace.restaurantbuddyandroid.database.DatabaseManager
import net.samuelcmace.restaurantbuddyandroid.database.dao.SessionDao
import org.json.JSONObject

abstract class Service(context: Context) {

    private var mContext: Context
    private var mDatabaseManager: DatabaseManager
    protected var mSessionDao: SessionDao

    private var mRequestQueue: RequestQueue

    init {
        this.mContext = context
        this.mDatabaseManager = Room.databaseBuilder(
            context,
            DatabaseManager::class.java, DatabaseManager.DATABASE_NAME
        ).build()
        this.mSessionDao = this.mDatabaseManager.sessionDao()
        this.mRequestQueue = Volley.newRequestQueue(this.mContext)
    }

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
