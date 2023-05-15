package net.samuelcmace.restaurantbuddyandroid.service

import android.content.Context
import com.android.volley.Request.Method
import kotlinx.coroutines.runBlocking
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item
import org.json.JSONObject

/**
 * Service class associated with all customer-service related operations.
 *
 * @constructor Initializes a new instance of CustomerService.
 */
class CustomerService(context: Context) : Service(context) {

    /**
     * Method to test whether the user is authenticated (by sending a dummy request to a valid API test route with
     * the JWT bearer token in the Authorization header).
     *
     * @param onSuccess A lambda function containing code to be executed after a successful API request.
     * @param onError A lambda function containing code to be executed after a failed API request.
     */
    fun testAuthorization(onSuccess: (response: JSONObject) -> Unit, onError: (errorMessage: String) -> Unit) {
        val url = "${AppConfig.getServerUrl()}/customer"
        authenticatedJSONRequest(url, Method.GET, null, onSuccess, onError)
    }

    /**
     * Method called to retrieve the menu from the restaurant API.
     *
     * @param onSuccess A lambda function containing code to be executed after a successful API request.
     * @param onError A lambda function containing code to be executed after a failed API request.
     */
    fun getMenu(onSuccess: (response: JSONObject) -> Unit, onError: (errorMessage: String) -> Unit) {
        val url = "${AppConfig.getServerUrl()}/customer/items"
        authenticatedJSONRequest(url, Method.GET, null, onSuccess, onError)
    }

    /**
     * Method called to check out the items in the shopping cart.
     *
     * @param onSuccess A lambda function containing code to be executed after a successful API request.
     * @param onError A lambda function containing code to be executed after a failed API request.
     */
    fun checkoutOrder(onSuccess: (response: JSONObject) -> Unit, onError: (errorMessage: String) -> Unit) {

        val url = "${AppConfig.getServerUrl()}/customer/order"
        val arrayListItems = ArrayList<Int>()

        runBlocking {
            for (item in mItemDao.getAll()) {
                item.dbId?.let { arrayListItems.add(it) }
            }
        }

        val jsonRequest = JSONObject()
        jsonRequest.put("menuItems", arrayListItems)

        authenticatedJSONRequest(url, Method.POST, jsonRequest, onSuccess, onError)

    }

    /**
     * Method called to store a menu item from the API in the cart (implemented in the Room database).
     *
     * @param item The item to be stored in the cart.
     */
    fun addItemToCart(item: Item) {
        runBlocking {
            mItemDao.insert(item)
        }
    }

    /**
     * Method called to delete an item from the shopping cart.
     *
     * @param item The item to be deleted from the cart.
     */
    fun removeItemFromCart(item: Item) {
        runBlocking {
            mItemDao.delete(item)
        }
    }

    /**
     * Method called to clear all items from the shopping cart.
     */
    fun clearShoppingCart() {
        runBlocking {
            mItemDao.clear()
        }
    }

    /**
     * Method to fetch all items stored in the cart.
     *
     * @return A set containing the items in the cart.
     */
    fun getCartItems(): MutableList<Item> {
        var data: MutableList<Item>
        runBlocking {
            data = mItemDao.getAll()
        }
        return data
    }

}
