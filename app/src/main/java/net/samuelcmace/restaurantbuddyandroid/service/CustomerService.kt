package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import com.android.volley.Request.Method
import kotlinx.coroutines.runBlocking
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item
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

    fun addItemToCart(item: Item, quantity: Int) {
        repeat(quantity) {
            runBlocking {
                mItemDao.insert(item)
            }
        }
    }

    fun getCartItems(): List<Item> {
        var data: List<Item>
        runBlocking {
            data = mItemDao.getAll()
        }
        return data
    }

}
