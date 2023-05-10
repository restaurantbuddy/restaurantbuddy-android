package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.runBlocking
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import net.samuelcmace.restaurantbuddyandroid.database.entity.Session

class CustomerService(context: Context) : Service(context) {

    private val mRequestQueue: RequestQueue = Volley.newRequestQueue(this.mContext)

    fun testAuthorization(onSuccess: (successMessage: String) -> Unit, onError: (errorMessage: String) -> Unit) {
        if (AppConfig.authToken == null)
            fetchActiveToken()

        val url = "${AppConfig.getServerUrl()}/customer"

        val request = object : JsonObjectRequest(
            Method.GET, url, null,
            {
                if (it.get("errorMessage").equals(null)) {
                    onSuccess(it.get("successMessage").toString())
                } else {
                    deleteActiveToken()
                    onError(it.get("errorMessage").toString())
                }
            },
            {
                deleteActiveToken()
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

    private fun fetchActiveToken() {
        var mSessionTokens: List<Session>

        runBlocking {
            mSessionTokens = mSessionDao.getAll()
            if (mSessionTokens.isNotEmpty())
                AppConfig.authToken = mSessionTokens.first()
        }
    }

    private fun deleteActiveToken() {
        AppConfig.authToken?.let {
            runBlocking {
                mSessionDao.deleteAll(it)
            }
        }
        AppConfig.authToken = null
    }

}
