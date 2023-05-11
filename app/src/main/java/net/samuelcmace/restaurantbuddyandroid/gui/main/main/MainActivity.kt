package net.samuelcmace.restaurantbuddyandroid.gui.main.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.samuelcmace.restaurantbuddyandroid.AppConfig
import net.samuelcmace.restaurantbuddyandroid.R
import net.samuelcmace.restaurantbuddyandroid.database.entity.Item
import net.samuelcmace.restaurantbuddyandroid.gui.auth.LoginActivity
import net.samuelcmace.restaurantbuddyandroid.gui.main.FeedbackActivity
import net.samuelcmace.restaurantbuddyandroid.gui.main.LocationsActivity
import net.samuelcmace.restaurantbuddyandroid.gui.main.cart.CartActivity
import net.samuelcmace.restaurantbuddyandroid.gui.main.jsonmodel.ItemModelCollection
import net.samuelcmace.restaurantbuddyandroid.service.AuthenticationService
import net.samuelcmace.restaurantbuddyandroid.service.CustomerService

/**
 * Activity representing the menu that the restaurant offers.
 *
 * @property mAuthenticationService Instance of the AuthenticationService used by the activity.
 * @property mCustomerService Instance of the CustomerService used by the activity.
 * @property rvMenu Instance of the RecyclerView used by the activity.
 * @property mMenuItemListAdapter Instance of the MenuItemAdapter used by the activity.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mAuthenticationService: AuthenticationService
    private lateinit var mCustomerService: CustomerService

    private lateinit var rvMenu: RecyclerView
    private lateinit var mMenuItemListAdapter: MenuItemAdapter

    /**
     * Method called by the Android API after the UI has been drawn.
     *
     * @param savedInstanceState The object to reference the previously-created activity if it is being recreated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mAuthenticationService = AuthenticationService(this)
        this.mCustomerService = CustomerService(this)

        this.rvMenu = findViewById(R.id.rvMenu)
        this.rvMenu.layoutManager = LinearLayoutManager(this)
        this.mMenuItemListAdapter = MenuItemAdapter(this)

        // First, fetch the active token to be used on the activity if it does
        // not already exist.
        if (AppConfig.authToken == null)
            this.mAuthenticationService.fetchActiveToken({
                loadMenu()
            }, {
                logout()
            })
        else
            loadMenu()

    }

    /**
     * Method called after the token has been verified to load the menu.
     */
    private fun loadMenu() {
        mCustomerService.getMenu({
            val jsonItems = Json.decodeFromString<ItemModelCollection>(it.toString())
            val menuItems: ArrayList<Item> = ArrayList()
            for (item in jsonItems.items) {
                menuItems.add(item.getDBItem())
            }
            this.mMenuItemListAdapter.setAllItems(menuItems)
            this.rvMenu.adapter = this.mMenuItemListAdapter
        }, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    /**
     * Method called by the Android API to load the menu bar for the activity.
     *
     * @param menu The menu to be inflated.
     * @return A boolean containing the result of the menu inflation process.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Method called by the Android API after a menu item has been selected.
     *
     * @param item The menu item that was selected.
     * @return A boolean containing the result of the menu item selection process.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.itCart -> {
                startActivity(Intent(this, CartActivity::class.java))
            }

            R.id.itLocations -> {
                startActivity(Intent(this, LocationsActivity::class.java))
            }

            R.id.itFeedback -> {
                startActivity(Intent(this, FeedbackActivity::class.java))
            }

            R.id.itLogout -> {
                logout()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Method called to log out the user from the system. This method deletes the token instance cached in the AppConfig
     * singleton instance, as well as deletes all tokens stored in the database.
     */
    private fun logout() {
        this.mAuthenticationService.logout {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}

