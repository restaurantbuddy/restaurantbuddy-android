package net.samuelcmace.restaurantbuddyandroid.gui.main.menu

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
import net.samuelcmace.restaurantbuddyandroid.gui.main.cart.CartActivity
import net.samuelcmace.restaurantbuddyandroid.gui.main.jsonmodel.ItemModelCollection
import net.samuelcmace.restaurantbuddyandroid.service.AuthenticationService
import net.samuelcmace.restaurantbuddyandroid.service.CustomerService

/**
 * Activity representing the menu that the restaurant offers.
 */
class MenuActivity : AppCompatActivity() {

    /**
     * Instance of the AuthenticationService used by the activity.
     */
    private lateinit var mAuthenticationService: AuthenticationService

    /**
     * Instance of the CustomerService used by the activity.
     */
    private lateinit var mCustomerService: CustomerService

    /**
     * Instance of the RecyclerView used by the activity.
     */
    private lateinit var rvMenu: RecyclerView

    /**
     * Instance of the MenuItemAdapter used by the activity.
     */
    private lateinit var mMenuItemListAdapter: MenuItemAdapter

    /**
     * Method called by the Android API after the UI has been drawn.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

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
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Method called by the Android API after a menu item has been selected.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.itCart -> {
                startActivity(Intent(this, CartActivity::class.java))
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

